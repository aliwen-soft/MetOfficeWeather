package training.metofficeweather;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class Main {
    public static void main(String args[]) {
        Client client = ClientBuilder.newClient();
        String name = client.target("")
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);

        Client newClient = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
    }
}	
