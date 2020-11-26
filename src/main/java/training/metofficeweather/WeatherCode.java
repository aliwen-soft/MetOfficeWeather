package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherCode {
    private String name;
    private String units;
    private String description;

    @JsonCreator
    public WeatherCode(@JsonProperty("name") String name, @JsonProperty("units") String units, @JsonProperty("$") String description) {
        this.name = name;
        this.units = units;
        this.description = description;
    }
}
