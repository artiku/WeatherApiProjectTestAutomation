package controller;

import exception.APIDataNotFoundException;
import exception.UnsuccessfulApiConnectionException;
import http_service.HTTPService;
import http_service.LinkGenerator;
import model.open_weather.current.OpenWeatherCurrentWeatherModel;
import model.open_weather.forecast.OpenWeatherForecastModel;
import weather_factory.CurrentWeatherFactory;
import weather_factory.ForecastFactory;

import java.io.IOException;
import java.net.UnknownHostException;

// OTHERS CALL THIS REPOSITORY. DON'T KNOW WHY. FOR ME REPOSITORY NAME DOES NOT READABLE :C MAY BE I DON'T GET SOMETHING
public class WeatherManager {

    private HTTPService httpService = new HTTPService();

    public OpenWeatherCurrentWeatherModel obtainCityCurrentWeather(String requestedWeatherCityName) throws APIDataNotFoundException {
        try {
            String link = LinkGenerator.generateWeatherLinkByCity(requestedWeatherCityName);

            httpService.createConnectionFromURL(link);

            String jsonData = httpService.getJsonFromConnection();
            httpService.close();

            return CurrentWeatherFactory.createCurrentWeatherFromJson(jsonData);
        } catch (UnknownHostException e) {
            throw new APIDataNotFoundException("No connection with the host.");
        } catch (UnsuccessfulApiConnectionException e) {
            throw new APIDataNotFoundException("Actually, entered by you city was not found. Sad. :C");
        } catch (IOException e) {
            e.printStackTrace();
            throw new APIDataNotFoundException("Data could not been obtained.");
        }
    }


    public OpenWeatherForecastModel obtainCityForecast(String requestedWeatherCityName) throws APIDataNotFoundException {
        try {
            String link = LinkGenerator.generateForecastLinkByCity(requestedWeatherCityName);

            httpService.createConnectionFromURL(link);

            String jsonData = httpService.getJsonFromConnection();
            httpService.close();

            return ForecastFactory.createForecastFromJson(jsonData);
        } catch (IOException e) {
            throw new APIDataNotFoundException("Actually, entered by you city was not found. Sad. :C");
        }
    }

    public void setHttpService(HTTPService httpService) {
        this.httpService = httpService;
    }

//    static OpenWeatherCurrentWeatherModel obtainCurrentWeatherFromApiSampleLink() {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        String link = LinkGenerator.linkForTest();
//
//        HTTPService httpService = new HTTPService(link);
//
//        String bufferData = httpService.bufferDataFromConnection();
//
//        OpenWeatherCurrentWeatherModel openWeatherCurrentWeather = gson.fromJson(bufferData, OpenWeatherCurrentWeatherModel.class);
//        return openWeatherCurrentWeather;
//    }


}
