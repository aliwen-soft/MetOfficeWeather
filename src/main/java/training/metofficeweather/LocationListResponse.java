package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LocationListResponse {
    @JsonProperty("Location")
    private List<Location> locations;
}
