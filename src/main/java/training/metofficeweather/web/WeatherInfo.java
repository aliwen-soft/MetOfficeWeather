package training.metofficeweather.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import training.metofficeweather.*;

import java.util.List;

@Getter
public class WeatherInfo {
    private final String locationId;
    private List<DataForDay> weatherDataByDay;

    private String hasError = "";

    public WeatherInfo(String location) {
        String locationIdTemp;
        boolean isId;
        try {
            Integer.parseInt(location);
            isId= true;
        } catch (NumberFormatException e) {
            isId= false;
        }
        if (isId) {
            locationIdTemp = location;
        }else{
            try {
                Location loc = MetAPIReader.getLocationFromName(location);
                locationIdTemp = loc.getId();
            } catch (InvalidLocNameException e) {
                locationIdTemp =null;
                hasError=e.getMessage();
            }

        }
        this.locationId = locationIdTemp;
    }

    public void populateData(){
        try {
            if(hasError.equals("")) {
                this.weatherDataByDay = MetAPIReader.getListOfWeatherDataPointsByDay(locationId);
            }
        } catch (JsonProcessingException e) {
            hasError = e.getMessage();
        } catch (InvalidIDException invalidIDException) {
            hasError = invalidIDException.getMessage();
        }
    }

}
