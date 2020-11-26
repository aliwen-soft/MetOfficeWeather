package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataAtLocation {
    private String i;
    private String lat;
    private String lon;
    private String name;
    private String country;
    private String continent;
    private String elevation;
    @JsonProperty("Period")
    private List<DataForDay> period;
}
