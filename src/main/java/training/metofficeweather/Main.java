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
        String baseURL = "http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/";
        String endpoint = "sitelist";
        String query = "?key=";
        String key = getAPIKey();

        String fullURL = baseURL + endpoint + query + key;

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
      //  Client client = ClientBuilder.newClient();
        String name = client.target(fullURL)
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Map<String, List<Location>>> mapLocs = objectMapper.readValue(name, new TypeReference<Map<String,Map<String, List<Location>>>>(){});
            List<Location> locations= mapLocs.get("Locations").get("Location");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }

    private static String getAPIKey() {
        Map<String, String> env = System.getenv();
        return env.get("MET_API_KEY");
    }
}
