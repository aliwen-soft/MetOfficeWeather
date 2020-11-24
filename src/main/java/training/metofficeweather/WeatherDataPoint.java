package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class WeatherDataPoint {
    private String windDirection;
    private String feelsLike;
    private String windGust;
    private String relativeHumidity;
    private String temperature;
    private String visibility;
    private String windSpeed;
    private String maxUV;
    private String weatherType;
    private String precipitationProbability;
    private String time;

    private String date;
    private boolean units = false;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isUnits() {
        return units;
    }

    public void setUnits(boolean units) {
        this.units = units;
    }

    public void addUnits(Map<String, DataKey> metaData){
        if(units==false){
            this.windDirection =this.windDirection+ metaData.get("D").getUnits();
            this.feelsLike =this.feelsLike+ metaData.get("F").getUnits();
            this.windGust =this.windGust+ metaData.get("G").getUnits();
            this.relativeHumidity =this.relativeHumidity+ metaData.get("H").getUnits();
            this.temperature =this.temperature+ metaData.get("T").getUnits();
            this.windSpeed =this.windSpeed+ metaData.get("S").getUnits();
            this.maxUV =this.maxUV+ metaData.get("U").getUnits();
            this.precipitationProbability =this.precipitationProbability+ metaData.get("Pp").getUnits();
            this.time =this.time+ metaData.get("$").getUnits();
            units= true;
        }
    }

    public String returnStringOfDataPoint(){
        String outputString = "";

        outputString = outputString + "Time: " + this.time +"\n";
        outputString = outputString + "Weather Type: " + this.weatherType +"\n";
        outputString = outputString + "Wind Direction: " + this.windDirection +"\n";
        outputString = outputString + "Feels Like: " + this.feelsLike +"\n";
        outputString = outputString + "WindGust: " + this.windGust +"\n";
        outputString = outputString + "Relative Humidity: " + this.relativeHumidity +"\n";
        outputString = outputString + "Temperature: " + this.temperature +"\n";
        outputString = outputString + "Visibility: " + this.visibility +"\n";
        outputString = outputString + "Wind Speed: " + this.windSpeed +"\n";
        outputString = outputString + "Max UV: " + this.maxUV +"\n";
        outputString = outputString + "Precipitation Probability: " + this.precipitationProbability +"\n";

        return outputString;
    }

    @JsonProperty("D")
    public String getWindDirection() {
        return windDirection;
    }

    @JsonProperty("D")
    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    @JsonProperty("F")
    public String getFeelsLike() {
        return feelsLike;
    }

    @JsonProperty("F")
    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    @JsonProperty("G")
    public String getWindGust() {
        return windGust;
    }

    @JsonProperty("G")
    public void setWindGust(String windGust) {
        this.windGust = windGust;
    }

    @JsonProperty("H")
    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    @JsonProperty("H")
    public void setRelativeHumidity(String relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    @JsonProperty("T")
    public String getTemperature() {
        return temperature;
    }

    @JsonProperty("T")
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @JsonProperty("V")
    public String getVisibility() {
        return visibility;
    }

    @JsonProperty("V")
    public void setVisibility(String visibility) {
    this.visibility = WeatherTypeDecoder.getVisibilityType(visibility);
    }

    @JsonProperty("S")
    public String getWindSpeed() {
        return windSpeed;
    }

    @JsonProperty("S")
    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    @JsonProperty("U")
    public String getMaxUV() {
        return maxUV;
    }

    @JsonProperty("U")
    public void setMaxUV(String maxUV) {
        this.maxUV = maxUV;
    }

    @JsonProperty("W")
    public String getWeatherType() {
        return weatherType;
    }

    @JsonProperty("W")
    public void setWeatherType(String weatherType) {
        this.weatherType = WeatherTypeDecoder.getWeatherType(weatherType);
    }

    @JsonProperty("Pp")
    public String getPrecipitationProbability() {
        return precipitationProbability;
    }

    @JsonProperty("Pp")
    public void setPrecipitationProbability(String precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    @JsonProperty("$")
    public String getTime() {
        return time;
    }

    @JsonProperty("$")
    public void setTime(String time) {
        this.time = time;
    }
}
