package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DataKey {
    private String name;
    private String units;
    private String description;

    @JsonCreator
    public DataKey(@JsonProperty("name") String name, @JsonProperty("units") String units, @JsonProperty("$") String description) {
        this.name = name;
        this.units = units;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @JsonProperty("$")
    public String getDescription() {
        return description;
    }
    @JsonProperty("$")
    public void setDescription(String description) {
        this.description = description;
    }
}
