package training.metofficeweather.web;

import java.util.List;

public class WeatherInfo {
    private final String locationId;
    private List<String> returned;

    public WeatherInfo(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationId() {
        return locationId;
    }
}
