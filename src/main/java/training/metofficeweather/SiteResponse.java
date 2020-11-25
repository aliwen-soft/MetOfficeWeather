package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteResponse {
    @JsonProperty("SiteRep")
    private MetResponse siteResponse;
}
