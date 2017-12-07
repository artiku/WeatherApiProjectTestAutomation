package weather_factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.open_weather.current.OpenWeatherCurrentWeatherModel;

public class CurrentWeatherFactory {

    public static OpenWeatherCurrentWeatherModel createCurrentWeatherFromJson(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(json, OpenWeatherCurrentWeatherModel.class);
    }
}
