package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class MetResponse {
    @JsonProperty("Wx")
    private Map<String, List<DataKey>> metaData;
    @JsonProperty("DV")
    private WeatherResponse dataValues;

    public Map<String, List<DataKey>> getMetaData() {
        return metaData;
    }

    public void setMetaData(Map<String, List<DataKey>> metaData) {
        this.metaData = metaData;
    }

    public WeatherResponse getDv() {
        return dataValues;
    }

    public void setDv(WeatherResponse dv) {
        this.dataValues = dv;
    }
}
