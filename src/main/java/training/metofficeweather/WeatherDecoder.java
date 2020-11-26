package training.metofficeweather;

import java.util.HashMap;
import java.util.Map;

public class WeatherDecoder {
    private static final Map<String, String> TYPE_MAP = new HashMap<String, String>() {{
        put("NA", "Not available");
        put("0", "Clear night");
        put("1", "Sunny day");
        put("2", "Partly cloudy (night)");
        put("3", "Partly cloudy (day)");
        put("4", "Not Used");
        put("5", "Mist");
        put("6", "Fog");
        put("7", "Cloudy");
        put("8", "Overcast");
        put("9", "Light rain shower (night)");
        put("10", "Light rain shower (day)");
        put("11", "Drizzle");
        put("12", "Light rain");
        put("13", "Heavy rain shower (night)");
        put("14", "Heavy rain shower (day)");
        put("15", "Heavy rain");
        put("16", "Sleet shower (night)");
        put("17", "Sleet shower (day)");
        put("18", "Sleet");
        put("19", "Hail shower (night)");
        put("20", "Hail shower (day)");
        put("21", "Hail");
        put("22", "Light snow shower (night)");
        put("23", "Light snow shower (day)");
        put("24", "Light snow");
        put("25", "Heavy snow shower (night)");
        put("26", "Heavy snow shower (day)");
        put("27", "Heavy snow");
        put("28", "Thunder shower (night)");
        put("29", "Thunder shower (day)");
        put("30", "Thunder");
    }};
    private static final Map<String, String> VISIBILITY_MAP = new HashMap<String, String>() {{
        put("UN", "Unknown");
        put("VP", "Very poor - Less than 1 km");
        put("PO", "Poor - Between 1-4 km");
        put("MO", "Moderate - Between 4-10 km");
        put("GO", "Good - Between 10-20 km");
        put("VG", "Very good - Between 20-40 km");
        put("EX", "Excellent - More than 40 km");
    }};

    private WeatherDecoder() {
    }

    public static String getWeatherType(String type) {
        return TYPE_MAP.get(type);
    }

    public static String getVisibilityType(String type) {
        return VISIBILITY_MAP.get(type);
    }

    public static String translateWeatherType(String name, String result) {
        if (name.equals("Weather Type")) {
            return getWeatherType(result);
        } else if (name.equals("Visibility")) {
            return getVisibilityType(result);
        } else {
            return result;
        }
    }
}
