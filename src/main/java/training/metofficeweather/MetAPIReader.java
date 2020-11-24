package training.metofficeweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetAPIReader {
    public static final List<Location> DEFAULT_LOCATIONS = getLocations();

    private static final String BASE_URL = "http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/";

    private MetAPIReader() {
    }

    public static void printWeatherFromName(String locName) throws JsonProcessingException {
        Location location = getLocationFromName(locName);
        printWeatherFromId(location.getId());
    }

    private static Location getLocationFromName(String locName) {
        Location location = DEFAULT_LOCATIONS.stream()
                .filter(loc -> locName.equals(loc.getName()))
                .findAny()
                .orElse(null);
        return location;
    }

    public static List<WeatherDataPoint> getListOfWeatherDataPointsFromName(String locName) throws JsonProcessingException {
        Location location = getLocationFromName(locName);
        return getListOfWeatherDataPoints(location.getId());
    }

    public static List<WeatherDataPoint> getListOfWeatherDataPoints(String locId) throws JsonProcessingException {
        List<WeatherDataPoint> datapoints = new ArrayList<>();
        if (locId != null) {
            Map<String, MetResponse> metResponseMap = getMETResponseMap(locId);
            if (metResponseMap.get("SiteRep").getDataValues().getLocation() != null) {
                List<DataForTime> dataForDays = metResponseMap.get("SiteRep").getDataValues().getLocation().getPeriod();
                Map<String, DataKey> dataKeyMap = getDataKeyMap(metResponseMap);
                for (DataForTime day : dataForDays) {
                    List<WeatherDataPoint> dataPoints = day.getRep();
                    for (WeatherDataPoint dataPoint : dataPoints) {
                        dataPoint.addUnits(dataKeyMap);
                        dataPoint.setDate(day.getValue());
                        datapoints.add(dataPoint);
                    }
                }
            } else {
                System.out.println("that is not a valid id");
            }
        } else {
            System.out.println("error null id");
        }
        return datapoints;
    }

    public static void printWeatherFromId(String locId) throws JsonProcessingException {
        List<WeatherDataPoint> dataPoints = getListOfWeatherDataPoints(locId);
        for (WeatherDataPoint dp : dataPoints) {
            System.out.println(dp.returnStringOfDataPoint());
        }
    }

    private static Map<String, MetResponse> getMETResponseMap(String locId) throws JsonProcessingException {
        String query = "?res=3hourly&key=";
        String fullURL = BASE_URL + locId + query + getAPIKey();
        String data = getData(fullURL);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(data, new TypeReference<Map<String, MetResponse>>() {
        });
    }

    private static Map<String, DataKey> getDataKeyMap(Map<String, MetResponse> weathermap) {
        List<DataKey> dataKeyList = weathermap.get("SiteRep").getMetaData().get("Param");
        Map<String, DataKey> dataKeyMap = new HashMap<>();
        for (DataKey key : dataKeyList) {
            dataKeyMap.put(key.getName(), key);
        }
        dataKeyMap.put("$", new DataKey("$", "Mins", "Time"));
        return dataKeyMap;
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
        } catch (JsonProcessingException e) {
            System.out.println("Critical: default locations could not be loaded.");
        }
        return locations;
    }

    private static String getData(String fullURL) {
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