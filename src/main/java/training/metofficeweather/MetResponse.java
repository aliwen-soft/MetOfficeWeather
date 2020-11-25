package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MetResponse {
    @JsonProperty("Wx")
    private Map<String, List<WeatherCode>> metaData;
    @JsonProperty("DV")
    private WeatherResponse dataValues;
}
