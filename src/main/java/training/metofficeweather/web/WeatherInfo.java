package training.metofficeweather.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import training.metofficeweather.*;

import java.util.List;

@Getter
public class WeatherInfo {
    private final String locationId;
    private List<DataForDay> weatherDataByDay;

    public WeatherInfo(String location) {
        boolean isId;
        try {
            Integer.parseInt(location);
            isId= true;
        } catch (NumberFormatException e) {
            isId= false;
        }

        if (isId) {
            this.locationId = location;
        }else{
            Location loc = MetAPIReader.getLocationFromName(location);
            this.locationId = loc.getId();
        }
    }

    public void populateData(){
        try {
            this.weatherDataByDay = MetAPIReader.getListOfWeatherDataPointsByDay(locationId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
