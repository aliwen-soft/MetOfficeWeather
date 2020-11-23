package training.metofficeweather;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class Main {
    public static void main(String args[]) {
        try {
          List<Location> locations= MetAPIReader.getLocations();
          MetAPIReader.printWeatherFromName(locations.get(1).getName(),locations);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
