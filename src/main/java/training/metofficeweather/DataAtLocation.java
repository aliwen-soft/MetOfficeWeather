package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DataAtLocation {
    private String i;
    private String lat;
    private String lon;
    private String name;
    private String country;
    private String continent;
    private String elevation;
    @JsonProperty("Period")
    private List<DataForTime> period;

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public List<DataForTime> getPeriod() {
        return period;
    }

    public void setPeriod(List<DataForTime> period) {
        this.period = period;
    }
}
