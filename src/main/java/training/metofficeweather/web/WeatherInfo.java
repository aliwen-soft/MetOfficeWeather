package training.metofficeweather.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import training.metofficeweather.MetAPIReader;

import java.util.List;

public class WeatherInfo {
    private final String locationId;
    private List<String> weatherData;

    public WeatherInfo(String locationId) {
        this.locationId = locationId;
    }

    public void populateData(){
        try {
            this.weatherData = MetAPIReader.returnWeatherFromId(locationId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public List<String> getWeatherData() {
        return weatherData;
    }

    public String getLocationId() {
        return locationId;
    }
}
