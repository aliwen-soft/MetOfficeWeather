package training.metofficeweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetAPIReader {
    public static final List<Location> DEFAULT_LOCATIONS = getLocations();

    private static final String BASE_URL = "http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/";

    public static void printWeatherFromName(String locName) throws JsonProcessingException {
        Location location = DEFAULT_LOCATIONS.stream()
                .filter(loc -> locName.equals(loc.getName()))
                .findAny()
                .orElse(null);
        printWeatherFromId(location.getId());
    }
    
    public static void printWeatherFromId(String locId) throws JsonProcessingException {
        if(locId != null) {
            Map<String, metResponse> metResponseMap = getMETResponseMap(locId);

            List<DataForTime> dataForDays = metResponseMap.get("SiteRep").getDv().getLocation().getPeriod();

            Map<String, DataKey> dataKeyMap = getDataKeyMap(metResponseMap);

            for (DataForTime day : dataForDays) {
                System.out.println("----" + day.getValue() + "-----");
                List<Map<String, String>> dataPoints = day.getRep();
                for (Map<String, String> dp : dataPoints) {
                    printDataPoint(dp, dataKeyMap);
                }
            }
        }else{
            System.out.println("error null id");
        }
    }

    private static Map<String, metResponse> getMETResponseMap(String locId) throws JsonProcessingException {
        String query= "?res=3hourly&key=";
        String fullURL = BASE_URL + locId + query + getAPIKey();
        String data = getData(fullURL);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(data, new TypeReference<Map<String, metResponse>>() {});
    }

    private static Map<String, DataKey> getDataKeyMap(Map<String, metResponse> weathermap) {
        List<DataKey> dataKeyList= weathermap.get("SiteRep").getMetaData().get("Param");
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

    public static List<Location> getLocations() {
        List<Location> locations = null;
        try {
            String fullURL = BASE_URL + "sitelist" + "?key=" + getAPIKey();
            String data = getData(fullURL);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Map<String, List<Location>>> mapLocs = objectMapper.readValue(data, new TypeReference<Map<String, Map<String, List<Location>>>>() {
            });
            locations = mapLocs.get("Locations").get("Location");
        }
        catch (JsonProcessingException e) {
            System.out.println("Critical: default locations could not be loaded.");
        }
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