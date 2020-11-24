package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class DataForTime {
    private String type;
    private String value;
    private List<WeatherDataPoint> rep;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty("Rep")
    public List<WeatherDataPoint> getRep() {
        return rep;
    }
    @JsonProperty("Rep")
    public void setRep(List<WeatherDataPoint> rep) {
        this.rep = rep;
    }
}
