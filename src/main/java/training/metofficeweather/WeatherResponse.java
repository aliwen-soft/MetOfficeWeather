package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class WeatherResponse {
    private Map<String, List<DataKey>> wx;
    private WeatherDoc dV;

    @JsonProperty("Wx")
    public Map<String, List<DataKey>> getWx() {
        return wx;
    }

    @JsonProperty("Wx")
    public void setWx(Map<String, List<DataKey>> wx) {
        this.wx = wx;
    }

    @JsonProperty("DV")
    public WeatherDoc getDv() {
        return dV;
    }

    @JsonProperty("DV")
    public void setDv(WeatherDoc dv) {
        this.dV = dv;
    }
}
