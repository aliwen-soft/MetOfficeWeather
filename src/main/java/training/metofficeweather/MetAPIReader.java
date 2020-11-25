package training.metofficeweather;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    public static List<List<WeatherDataPoint>> getListOfWeatherDataPointsFromNameByDay(String locName) throws JsonProcessingException {
        Location location = getLocationFromName(locName);
        return getListOfWeatherDataPointsByDay(location.getId());
    }

    public static List<List<WeatherDataPoint>> getListOfWeatherDataPointsByDay(String locId) throws JsonProcessingException {
        List<List<WeatherDataPoint>> weatherDataPointsByDay = new ArrayList<>();
        if (locId != null) {
            MetResponse metResponse = getMETResponse(locId);
            if (metResponse.getDataValues().getLocation() != null) {
                List<DataForTime> dataForDays = metResponse.getDataValues().getLocation().getPeriod();
                Map<String, WeatherCode> dataKeyMap = getDataKeyMap(metResponse);
                for (DataForTime day : dataForDays) {
                    List<WeatherDataPoint> dataPoints = day.getWeatherDataPoints();
                    for (WeatherDataPoint dataPoint : dataPoints) {
                        dataPoint.addUnits(dataKeyMap);
                        dataPoint.setDate(day.getValue());
                    }
                    weatherDataPointsByDay.add(dataPoints);
                }
            } else {
                System.out.println("that is not a valid id");
            }
        } else {
            System.out.println("error null id");
        }
        return weatherDataPointsByDay;
    }

    public static void printWeatherFromId(String locId) throws JsonProcessingException {
        List<List<WeatherDataPoint>> dataPointsbyday = getListOfWeatherDataPointsByDay(locId);
        for(List<WeatherDataPoint> dataPoints: dataPointsbyday){
            for (WeatherDataPoint dp : dataPoints) {
                System.out.println(dp.returnStringOfDataPoint());
            }
        }
    }

    private static MetResponse getMETResponse(String locId) throws JsonProcessingException {
        String query = "?res=3hourly&key=";
        String fullURL = BASE_URL + locId + query + getAPIKey();
        String data = getData(fullURL);
        ObjectMapper objectMapper = new ObjectMapper();

        SiteResponse metResponseMap = objectMapper.readValue(data, SiteResponse.class);

        return metResponseMap.getSiteResponse();
    }

    private static Map<String, WeatherCode> getDataKeyMap(MetResponse metResponse) {
        List<WeatherCode> weatherCodeList = metResponse.getMetaData().get("Param");
        Map<String, WeatherCode> weatherCodeMap = new HashMap<>();
        for (WeatherCode weatherCode : weatherCodeList) {
            weatherCodeMap.put(weatherCode.getName(), weatherCode);
        }
        weatherCodeMap.putIfAbsent("$", new WeatherCode("$", "Mins", "Time"));
        return weatherCodeMap;
    }

    public static List<Location> getLocations() {
        List<Location> locations = null;
        try {
            String fullURL = BASE_URL + "sitelist" + "?key=" + getAPIKey();
            String data = getData(fullURL);
            ObjectMapper objectMapper = new ObjectMapper();
            LocationSiteResponse locationSiteResponse = objectMapper.readValue(data,LocationSiteResponse.class);
            locations = locationSiteResponse.getLocations().getLocationList();
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