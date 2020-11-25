package training.metofficeweather.web;

import lombok.Getter;
import lombok.Setter;
import training.metofficeweather.Location;
import training.metofficeweather.MetAPIReader;

import java.util.List;

@Getter
@Setter
public class LocationInfo {
    private List<Location> locationList = MetAPIReader.DEFAULT_LOCATIONS;;
}
