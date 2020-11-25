package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class DataForTime {
    private String type;
    private String value;
    @JsonProperty("Rep")
    private List<WeatherDataPoint> weatherDataPoints;
}
