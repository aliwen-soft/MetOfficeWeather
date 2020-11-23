package training.metofficeweather;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.Map;

public class Main {
    public static void main(String args[]) {
        String baseURL = "http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/";
        String endpoint = "sitelist";
        String query = "?key=";
        String key = getAPIKey();

        String fullURL = baseURL + endpoint + query + key;

        Client client = ClientBuilder.newClient();
        String name = client.target(fullURL)
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);

        Client newClient = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
    }

    private static String getAPIKey() {
        Map<String, String> env = System.getenv();
        return env.get("MET_API_KEY");
    }
}
