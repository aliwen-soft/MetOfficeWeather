package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class WeatherDataPoint {
    @JsonProperty("D")
    private String windDirection;
    @JsonProperty("F")
    private String feelsLike;
    @JsonProperty("G")
    private String windGust;
    @JsonProperty("H")
    private String relativeHumidity;
    @JsonProperty("T")
    private String temperature;
    @JsonProperty("V")
    private String visibility;
    @JsonProperty("S")
    private String windSpeed;
    @JsonProperty("U")
    private String maxUV;
    @JsonProperty("W")
    private String weatherType;
    @JsonProperty("Pp")
    private String precipitationProbability;
    @JsonProperty("$")
    private String time;

    private String date;
    private boolean hasUnits = false;

    public void setVisibility(String visibility) {
        this.visibility = WeatherDecoder.getVisibilityType(visibility);
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = WeatherDecoder.getWeatherType(weatherType);
    }

    public void setTime(String time) {
        int hours = Integer.parseInt(time) / 60;
        this.time = hours + ":00";
    }

    public void addUnits(Map<String, WeatherCode> metaData) {
        if (!hasUnits) {
            this.feelsLike = this.feelsLike + "°" + metaData.get("F").getUnits();
            this.windGust = this.windGust + metaData.get("G").getUnits();
            this.relativeHumidity = this.relativeHumidity + metaData.get("H").getUnits();
            this.temperature = this.temperature + "°" + metaData.get("T").getUnits();
            this.windSpeed = this.windSpeed + metaData.get("S").getUnits();
            this.maxUV = this.maxUV + metaData.get("U").getUnits();
            this.precipitationProbability = this.precipitationProbability + metaData.get("Pp").getUnits();
            hasUnits = true;
        }
    }

    public String returnStringOfDataPoint() {
        String outputString = "";

        outputString = outputString + "Time: " + this.time + "\n";
        outputString = outputString + "Weather Type: " + this.weatherType + "\n";
        outputString = outputString + "Wind Direction: " + this.windDirection + "\n";
        outputString = outputString + "Feels Like: " + this.feelsLike + "\n";
        outputString = outputString + "Wind Gust: " + this.windGust + "\n";
        outputString = outputString + "Relative Humidity: " + this.relativeHumidity + "\n";
        outputString = outputString + "Temperature: " + this.temperature + "\n";
        outputString = outputString + "Visibility: " + this.visibility + "\n";
        outputString = outputString + "Wind Speed: " + this.windSpeed + "\n";
        outputString = outputString + "Max UV: " + this.maxUV + "\n";
        outputString = outputString + "Precipitation Probability: " + this.precipitationProbability + "\n";

        return outputString;
    }
}
