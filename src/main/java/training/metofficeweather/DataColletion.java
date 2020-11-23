package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DataColletion {
    private String i;
    private String latval;
    private String longval;
    private String name;
    private String country;
    private String continent;
    private String elevation;
    private List<DataDays> period;

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    @JsonProperty("lat")
    public String getLatval() {
        return latval;
    }
    @JsonProperty("lat")
    public void setLatval(String latval) {
        this.latval = latval;
    }
    @JsonProperty("lon")
    public String getLongval() {
        return longval;
    }
    @JsonProperty("lon")
    public void setLongval(String longval) {
        this.longval = longval;
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

    @JsonProperty("Period")
    public List<DataDays> getPeriod() {
        return period;
    }
    @JsonProperty("Period")
    public void setPeriod(List<DataDays> period) {
        this.period = period;
    }
}
