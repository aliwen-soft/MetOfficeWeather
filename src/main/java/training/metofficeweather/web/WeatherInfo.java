package training.metofficeweather.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import training.metofficeweather.*;

import java.util.List;

@Getter
public class WeatherInfo {
    private final String locationId;
    private List<DataForDay> weatherDataByDay;

    private String error = "";

    public WeatherInfo(String locationReference) {
        String numericId;
        boolean isNumeric;
        try{
            Integer.parseInt(locationReference);
            isNumeric = true;
        } catch (NumberFormatException e) {
            isNumeric = false;
        }
        if (isNumeric) {
            numericId = locationReference;
        } else {
            try {
                Location loc = MetAPIReader.getLocationFromName(locationReference);
                numericId = loc.getId();
            } catch (InvalidLocNameException e) {
                numericId = null;
                error = e.getMessage();
            }
        }
        this.locationId = numericId;
    }

    public void populateData() {
        try {
            if (error.equals(""))
                this.weatherDataByDay = MetAPIReader.getListOfWeatherDataPointsByDay(locationId);
        } catch (JsonProcessingException | InvalidIDException e) {
            this.weatherDataByDay = null;
            error = e.getMessage();
        }
    }

    public void populateRestrictedData(int dayLimit) {
        populateData();
        if (weatherDataByDay == null)
            return;
        int maxDayListIndex = Math.min(dayLimit, weatherDataByDay.size());
        weatherDataByDay = weatherDataByDay.subList(0, maxDayListIndex);
        if (0 < maxDayListIndex)
            weatherDataByDay.get(0).setDayOfTheWeek();
    }

}
