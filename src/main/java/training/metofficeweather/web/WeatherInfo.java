package training.metofficeweather.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import training.metofficeweather.MetAPIReader;
import training.metofficeweather.WeatherDataPoint;

import java.util.List;

public class WeatherInfo {
    private final String locationId;
    private List<WeatherDataPoint> weatherData;

    public WeatherInfo(String locationId) {
        this.locationId = locationId;
    }

    public void populateData(){
        try {
            this.weatherData = MetAPIReader.getListOfWeatherDataPoints(locationId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public List<WeatherDataPoint> getWeatherData() {
        return weatherData;
    }

    public String getLocationId() {
        return locationId;
    }
}
