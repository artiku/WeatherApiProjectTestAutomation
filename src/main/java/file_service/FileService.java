package file_service;

import model.open_weather.current.OpenWeatherCurrentWeatherModel;
import model.open_weather.forecast.OpenWeatherForecastModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


// TODO: No file found exception. And test for it.

public class FileService {

    public String[] readLinesFromInputFile(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
            return lines.toArray(new String[lines.size()]);
        } catch (IOException e) {
            System.err.println("File could not be read");
            return null;
        }
    }

    public void writeIntoFile(String cityName, OpenWeatherCurrentWeatherModel owModel, OpenWeatherForecastModel forecastModel) {
        try {
            PrintWriter writer = new PrintWriter("src/main/output/" + cityName + ".txt", "UTF-8");

            writer.println(owModel);
            writer.println(forecastModel);
            System.out.println(cityName + " weather war recorded");
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Can not be written into file");
        }
    }

}
