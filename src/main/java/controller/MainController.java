package controller;

import file_service.FileService;
import model.open_weather.current.OpenWeatherCurrentWeatherModel;
import model.open_weather.forecast.OpenWeatherForecastModel;

import java.util.Scanner;

public class MainController {


    public static void main(String[] args) {
        System.out.print("Enter 1 if you want to read console input\n" +
                "or 2 if you want to read from input.txt file\n");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                scanner.nextLine();
                if (input == 1) {
                    doConsoleInput(scanner);
                    break;
                } else if (input == 2) {
                    doFileInput();
                    break;
                } else {
                    System.out.println("1 or 2, please.");
                }
            } else {
                scanner.next();
                System.out.println("Valid input: 1 (console) or 2 (file).");
            }
        }

        scanner.close();

    }

    private static void doConsoleInput(Scanner scanner) {
        while (true) {
            System.out.println("Enter city name weather to obtain or 'q' to exit");
            if (scanner.hasNext()) {
                String input = scanner.nextLine();
                if ("q".equals(input)) {
                    System.out.println("Exit!");
                    break;
                } else {
                    String city = input.toUpperCase();

                    OpenWeatherCurrentWeatherModel currentWeatherModel = WeatherRequest.obtainCityCurrentWeather(city);
                    System.out.println(currentWeatherModel);
                    OpenWeatherForecastModel forecastModel = WeatherRequest.obtainCityForecast(city);
                    System.out.println(forecastModel);
                }
            }
        }
    }

    private static void doFileInput() {
        FileService fileService = new FileService();
        String[] input = fileService.readLinesFromInputFile("src/main/java/input/input.txt");
        for (String city : input) {
            OpenWeatherCurrentWeatherModel currentWeatherModel = WeatherRequest.obtainCityCurrentWeather(city);
            OpenWeatherForecastModel forecastModel = WeatherRequest.obtainCityForecast(city);
            fileService.writeIntoFile(city, currentWeatherModel, forecastModel);


        }
    }
}
