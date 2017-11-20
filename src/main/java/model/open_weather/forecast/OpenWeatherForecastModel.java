package model.open_weather.forecast;

import com.google.gson.internal.LinkedTreeMap;
import model.APIModel;

import java.util.HashMap;

public class OpenWeatherForecastModel implements APIModel {

    private ForecastDay[] forecastDayArray = new ForecastDay[3];

    public HashMap<String, Object> city;
    public HashMap<String, Object>[] list;

    public String getLocationName() {
        return (String) city.get("name");
    }

    public void arrangeThreeDayForecast() {
        int counter = 0;
        for (HashMap<String, Object> day : list) {
            if (counter == 3) break;
            ForecastDay forecastDay = new ForecastDay(day);
            forecastDayArray[counter++] = forecastDay;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Min/Max temperature forecast for the next 3 days:\n");
        for (ForecastDay day:
        forecastDayArray) {
            sb.append(String.format("(Min: %.2f | Max: %.2f)\n", day.getTempMinCelsius(), day.getTempMaxCelsius()));
        }
        return sb.toString();
    }
}
