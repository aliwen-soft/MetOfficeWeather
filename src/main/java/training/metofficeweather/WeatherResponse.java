package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
    private String dataDate;
    private String type;
    @JsonProperty("Location")
    private DataAtLocation location;
}
