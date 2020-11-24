package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {
    private String dataDate;
    private String type;
    @JsonProperty("Location")
    private DataAtLocation location;

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

    public DataAtLocation getLocation() {
        return location;
    }

    public void setLocation(DataAtLocation location) {
        this.location = location;
    }
}
