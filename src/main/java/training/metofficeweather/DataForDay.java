package training.metofficeweather;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter
public class DataForDay extends DataForTime {
    private String dayOfTheWeek = "unknown";

    @Override
    public void setTimeValue(String timeValue) {
        if (timeValue.endsWith("Z")) {
            timeValue = timeValue.substring(0, timeValue.length() - 1);
        }
        this.timeValue = timeValue;
    }

    public void setDayOfTheWeek() {
        String dateFromString = getTimeValue();
        LocalDate localDate = LocalDate.parse(dateFromString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        dayOfTheWeek = dayOfWeek.getDisplayName(TextStyle.FULL, new Locale("en", "GB"));
    }
}

