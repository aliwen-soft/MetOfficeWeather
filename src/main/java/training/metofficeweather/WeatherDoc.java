package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class WeatherDoc {
    private String dataDate;
    private String type;
    private DataColletion location;

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("Location")
    public DataColletion getLocation() {
        return location;
    }
    @JsonProperty("Location")
    public void setLocation(DataColletion location) {
        this.location = location;
    }
}
