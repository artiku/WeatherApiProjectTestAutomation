package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import http_service.HTTPService;
import http_service.LinkGenerator;
import model.open_weather.current.OpenWeatherCurrentWeatherModel;
import model.open_weather.forecast.OpenWeatherForecastModel;

public class WeatherRequest {

    // TODO: Static = bad?
    static OpenWeatherCurrentWeatherModel obtainCityCurrentWeather(String cityName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String link = LinkGenerator.generateWeatherLinkByCity(cityName);

        HTTPService httpService = new HTTPService(link);

        String bufferData = httpService.bufferDataFromConnection();
        if (bufferData.equals("Connection has not been established.")) {
            return null;
        } else {
            return gson.fromJson(bufferData, OpenWeatherCurrentWeatherModel.class);
        }
    }

    static OpenWeatherForecastModel obtainCityForecast(String cityName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String link = LinkGenerator.generateForecastLinkByCity(cityName);

        HTTPService httpService = new HTTPService(link);

        String bufferData = httpService.bufferDataFromConnection();
        if (bufferData.equals("Connection has not been established.")) {
            return null;
        } else {

            OpenWeatherForecastModel forecastModel = gson.fromJson(bufferData, OpenWeatherForecastModel.class);;
            forecastModel.arrangeThreeDayForecast();
            return forecastModel;
        }
    }

    static OpenWeatherCurrentWeatherModel obtainCurrentWeatherFromApiSampleLink() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String link = LinkGenerator.linkForTest();

        HTTPService httpService = new HTTPService(link);

        String bufferData = httpService.bufferDataFromConnection();

        OpenWeatherCurrentWeatherModel openWeatherCurrentWeather = gson.fromJson(bufferData, OpenWeatherCurrentWeatherModel.class);
        return openWeatherCurrentWeather;
    }


}
