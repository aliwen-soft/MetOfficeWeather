package training.metofficeweather;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private String elevation;
    private String id;
    private String latitude;
    private String longitude;
    private String name;
    private String region;
    private String unitaryAuthArea;
    private String obsSource;
    private String nationalPark;

    public void prinloc(){
        System.out.println(this.name);
    }
}
