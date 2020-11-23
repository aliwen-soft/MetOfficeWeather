package training.metofficeweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.collections.MappingChange;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetAPIReader {

    private static final String BASE_URL = "http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/";

    public static void printWeatherFromName(String locName, List<Location> locations) throws JsonProcessingException {
        Location location = locations.stream()
                .filter(loc -> locName.equals(loc.getName()))
                .findAny()
                .orElse(null);
        printWeatherFromId(location.getId());
    }
    public static void printWeatherFromId(String locId) throws JsonProcessingException {
        String fullURL = BASE_URL + locId + "?res=3hourly&key=" + getAPIKey();
        String data= getData(fullURL);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,  WeatherResponse > weathermap = objectMapper.readValue(data, new TypeReference<Map<String, WeatherResponse>>() {});

        List<DataDays> dataDays =weathermap.get("SiteRep").getDv().getLocation().getPeriod();

        Map<String, DataKey> dataKeyMap = getDataKeyMap(weathermap);

        for (DataDays day:dataDays) {
            System.out.println("----"+day.getValue()+"-----");
            List<Map<String, String>> dataPoints = day.getRep();
            for(Map<String, String> dp :dataPoints){
                printDataPoint(dp,dataKeyMap);
            }
        }
    }


    private static Map<String, DataKey> getDataKeyMap(Map<String, WeatherResponse> weathermap) {
        List<DataKey> dataKeyList= weathermap.get("SiteRep").getWx().get("Param");
        Map<String,DataKey> dataKeyMap=new HashMap<>();
        for(DataKey key :dataKeyList){
            dataKeyMap.put(key.getName(),key);
        }
        dataKeyMap.put("$",new DataKey("$","Mins", "Time"));
        return dataKeyMap;
    }

    private static void printDataPoint(Map<String,String> datapoint, Map<String, DataKey> keys){
        for(String dp: datapoint.keySet()){
            DataKey key =keys.get(dp);
            String decodedDataPoint= WeatherTypeDecoder.translateWeatherType(key.getDescription(),datapoint.get(dp));
            System.out.println(key.getDescription() + ": "+ decodedDataPoint+" "+ key.getUnits());
        }
    }

    public static List<Location> getLocations() throws JsonProcessingException {
        String fullURL = BASE_URL + "sitelist" + "?key=" + getAPIKey();
        String data=getData(fullURL);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Map<String, List<Location>>> mapLocs = objectMapper.readValue(data, new TypeReference<Map<String, Map<String, List<Location>>>>() {
        });
        List<Location> locations = mapLocs.get("Locations").get("Location");
        return locations;
    }

    private static String getData(String fullURL){
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        String data = client.target(fullURL)
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);
        return data;
    }
    private static String getAPIKey() {
        Map<String, String> env = System.getenv();
        return env.get("MET_API_KEY");
    }
}