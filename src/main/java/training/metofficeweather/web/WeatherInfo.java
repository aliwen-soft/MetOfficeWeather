package training.metofficeweather.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import training.metofficeweather.MetAPIReader;
import training.metofficeweather.WeatherDataPoint;

import java.util.List;

@Getter
public class WeatherInfo {
    private final String locationId;
    private List<List<WeatherDataPoint>> weatherDataByDay;

    public WeatherInfo(String locationId) {
        this.locationId = locationId;
    }

    public void populateData(){
        try {
            this.weatherDataByDay = MetAPIReader.getListOfWeatherDataPointsByDay(locationId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
