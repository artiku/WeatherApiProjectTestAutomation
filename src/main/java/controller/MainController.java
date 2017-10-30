package controller;

import model.open_weather.OpenWeatherCurrentWeatherModel;

public class MainController {
    public static void main(String[] args) {
        OpenWeatherCurrentWeatherModel currentWeather = WeatherRequest.obtainCurrentWeatherFromApi();
        System.out.println(currentWeather);
    }
}
