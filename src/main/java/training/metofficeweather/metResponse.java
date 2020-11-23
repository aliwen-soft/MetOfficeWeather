package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class metResponse {
    private Map<String, List<DataKey>> metaData;
    private WeatherResponse dataValues;

    @JsonProperty("Wx")
    public Map<String, List<DataKey>> getMetaData() {
        return metaData;
    }

    @JsonProperty("Wx")
    public void setMetaData(Map<String, List<DataKey>> metaData) {
        this.metaData = metaData;
    }

    @JsonProperty("DV")
    public WeatherResponse getDv() {
        return dataValues;
    }

    @JsonProperty("DV")
    public void setDv(WeatherResponse dv) {
        this.dataValues = dv;
    }
}
