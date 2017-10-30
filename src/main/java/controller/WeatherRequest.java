package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import http_service.HTTPService;
import http_service.LinkGenerator;
import model.open_weather.OpenWeatherCurrentWeatherModel;


class WeatherRequest {

    static OpenWeatherCurrentWeatherModel obtainCurrentWeatherFromApi(String cityName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String link = LinkGenerator.generateWeatherLinkByCity(cityName);

        HTTPService httpService = new HTTPService(link);

        String bufferData = httpService.bufferDataFromConnection();

        return gson.fromJson(bufferData, OpenWeatherCurrentWeatherModel.class);
    }

    static OpenWeatherCurrentWeatherModel obtainCurrentWeatherFromApi() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // connect --- buffer data ---

        // How do I get input?
        //String link = LinkGenerator.generateWeatherLinkByCity("TALLINN");
        String link = LinkGenerator.linkForTest();

        // TODO: Create exception for wrong url and unsuccessful connection
        HTTPService httpService = new HTTPService(link);

        // TODO: Some more exceptions
        String bufferData = httpService.bufferDataFromConnection();
        //System.out.println(bufferData);

        // TODO: Even more exceptions?
        OpenWeatherCurrentWeatherModel openWeatherCurrentWeather = gson.fromJson(bufferData, OpenWeatherCurrentWeatherModel.class);
        return openWeatherCurrentWeather;
    }


}
