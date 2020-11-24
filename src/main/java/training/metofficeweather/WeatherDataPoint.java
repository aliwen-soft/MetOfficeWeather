package training.metofficeweather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

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


    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getWindGust() {
        return windGust;
    }

    public void setWindGust(String windGust) {
        this.windGust = windGust;
    }

    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(String relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
    this.visibility = WeatherTypeDecoder.getVisibilityType(visibility);
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getMaxUV() {
        return maxUV;
    }

    public void setMaxUV(String maxUV) {
        this.maxUV = maxUV;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = WeatherTypeDecoder.getWeatherType(weatherType);
    }

    public String getPrecipitationProbability() {
        return precipitationProbability;
    }

    public void setPrecipitationProbability(String precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
