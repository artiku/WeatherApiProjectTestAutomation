package file_service_test;

import console.ConsoleReader;
import controller.ConsoleController;
import controller.WeatherManager;
import file_service.FileService;
import http_service.HTTPService;
import model.open_weather.current.OpenWeatherCurrentWeatherModel;
import model.open_weather.forecast.OpenWeatherForecastModel;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FileServiceTest {


        @Test
    public void testReadFromFile() {
        FileService mockedFileService = mock(FileService.class);
        ConsoleController consoleController = new ConsoleController(new ConsoleReader());
        consoleController.setFileService(mockedFileService);
        String[] exampleFileReadOutput = {"Tallinn", "Tapa", "Tartu"};
        when(mockedFileService.readLinesFromInputFile(anyString())).thenReturn(exampleFileReadOutput);
        consoleController.doFileInput();
        verify(mockedFileService).readLinesFromInputFile(anyString());
    }

    @Test
    public void testThreeTimesWrittenIntoFile() throws IOException {
        String JSON_EXAMPLE = "{\"list\":[{\"main\":{\"temp_min\":259.086,\"temp_max\":261.45}}," +
                "{\"main\": {\"temp_min\":259.086,\"temp_max\":261.45}},{\"main\":{\"temp_min\":259.086,\"temp_max\":261.45}}]}";

        FileService mockedFileService = mock(FileService.class);
        ConsoleController consoleController = new ConsoleController(new ConsoleReader());

        WeatherManager weatherManager = new WeatherManager();
        HTTPService mockedHttpService = mock(HTTPService.class);
//        when(mockedHttpService.createConnectionFromURL(anyString()));
        when(mockedHttpService.getJsonFromConnection()).thenReturn(JSON_EXAMPLE);
        weatherManager.setHttpService(mockedHttpService);
        consoleController.setWeatherManager(weatherManager);

        consoleController.setFileService(mockedFileService);
        String[] exampleFileReadOutput = {"Tallinn", "Tapa", "Tartu"};
        when(mockedFileService.readLinesFromInputFile(anyString())).thenReturn(exampleFileReadOutput);
        consoleController.doFileInput();
        verify(mockedFileService, times(3)).writeIntoFile(anyString(),
                any(OpenWeatherCurrentWeatherModel.class), any(OpenWeatherForecastModel.class));
    }

}
