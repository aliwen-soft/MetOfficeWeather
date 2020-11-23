package training.metofficeweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String args[]) {
        try {
          List<Location> locations= MetAPIReader.getLocations();
          MetAPIReader.getWeather(locations.get(0).getId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static String getAPIKey() {
        Map<String, String> env = System.getenv();
        return env.get("MET_API_KEY");
    }
}
