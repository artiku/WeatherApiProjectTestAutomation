package controller;

import console.ConsoleReader;
import exception.APIDataNotFoundException;
import file_service.FileService;
import model.open_weather.current.OpenWeatherCurrentWeatherModel;
import model.open_weather.forecast.OpenWeatherForecastModel;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleController {

    private static final String INPUT_PATH = "src/main/java/input/input.txt";

    private ConsoleReader consoleReader;

    private FileService fileService = new FileService();

    private WeatherManager weatherManager = new WeatherManager();

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public void setWeatherManager(WeatherManager weatherManager) {
        this.weatherManager = weatherManager;
    }

    public ConsoleController(ConsoleReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    public static void main(String[] args) {
        new ConsoleController(new ConsoleReader()).start();
    }

    private int askUserForInputType() {
        System.out.println("Enter 1 if you want to read console input\n" +
                        "or 2 if you want to read from input.txt file");
        String input;
        while (!(input = consoleReader.readNextLineFromConsole("Valid input: 1 (console) or 2 (file)."))
                .matches("[1-2]")) {
            System.out.println("Incorrect input.");
        }
        return Integer.parseInt(input);
    }

    public void start() {
        int inputTypeMarker = askUserForInputType();

        switch (inputTypeMarker){
            case 1:
                doConsoleInput();
                break;
            case 2:
                doFileInput();
                break;
            default:
                //TODO: Throw Exception?!?
                consoleReader.close();
                System.err.println("Something is wrong with the input");
        }
    }


    private void printWeatherReport(OpenWeatherCurrentWeatherModel currentWeatherModel, OpenWeatherForecastModel forecastModel) {
        System.out.println(currentWeatherModel);
        System.out.println(forecastModel);
    }

    private void doConsoleInput() {
        String input;
        while (!(input = consoleReader.readNextLineFromConsole("Enter city name which weather to obtain or 'q' to exit"))
                .equals("q")) {
            try {
                String city = input.toUpperCase();
                printWeatherReport(weatherManager.obtainCityCurrentWeather(city), weatherManager.obtainCityForecast(city));
            } catch (APIDataNotFoundException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
        System.out.println("Exit!");
        consoleReader.close();
    }

    public void doFileInput() {
        try {
            String[] input = fileService.readLinesFromInputFile(INPUT_PATH);
            for (String city : input) {
                fileService.writeIntoFile(city, weatherManager.obtainCityCurrentWeather(city), weatherManager.obtainCityForecast(city));
            }

        } catch (APIDataNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
