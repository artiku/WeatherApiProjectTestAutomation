package weather_factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.open_weather.forecast.OpenWeatherForecastModel;

public class ForecastFactory {

    public static OpenWeatherForecastModel createForecastFromJson(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        OpenWeatherForecastModel forecastModel = gson.fromJson(json, OpenWeatherForecastModel.class);
        forecastModel.arrangeThreeDayForecast();
        return forecastModel;
    }
}
