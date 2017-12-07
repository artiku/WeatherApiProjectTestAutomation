package controller_test;

import console.ConsoleReader;
import controller.ConsoleController;
import controller.WeatherManager;
import file_service.FileService;
import model.open_weather.current.OpenWeatherCurrentWeatherModel;
import model.open_weather.forecast.OpenWeatherForecastModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ConsoleControllerTest {

    private WeatherManager mockedWeatherManager;

    @Before
    public void setupTestObjects() {

//        ConsoleReader mockedConsoleReader = mock(ConsoleReader.class);
//        ConsoleController consoleController = new ConsoleController(mockedConsoleReader);
        mockedWeatherManager = mock(WeatherManager.class);
//        consoleController.setWeatherManager(mockedWeatherManager);

        OpenWeatherCurrentWeatherModel mockedCurrentWeatherModel = mock(OpenWeatherCurrentWeatherModel.class);
        OpenWeatherForecastModel mockedForecastModel = mock(OpenWeatherForecastModel.class);

        when(mockedWeatherManager.obtainCityForecast(anyString())).thenReturn(mockedForecastModel);
        when(mockedWeatherManager.obtainCityCurrentWeather(anyString())).thenReturn(mockedCurrentWeatherModel);
    }


    @Test
    public void testWeatherObjectMethodsInvoked() {
        ConsoleReader mockedConsoleReader = mock(ConsoleReader.class);
        ConsoleController consoleController = new ConsoleController(mockedConsoleReader);
        consoleController.setWeatherManager(mockedWeatherManager);

        when(mockedConsoleReader.readNextLineFromConsole(anyString())).thenAnswer(new Answer() {
            private int count = 0;

            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                switch (count++) {
                    case 0:
                        return "1";

                    case 1:
                        return "Tallinn";

                    case 2:
                        return "Tapa";

                    case 3:
                        return "Tartu";

                    default:
                        return "q";
                }
            }
        });

        consoleController.start();


        verify(mockedWeatherManager, times(3)).obtainCityCurrentWeather(anyString());
        verify(mockedWeatherManager).obtainCityForecast("TALLINN");
        verify(mockedWeatherManager).obtainCityForecast("TAPA");
        verify(mockedWeatherManager).obtainCityForecast("TARTU");
        verify(mockedConsoleReader, times(5)).readNextLineFromConsole(anyString());
    }

    @Test
    public void testConsoleReaderInvokedAdditionalTimeIncorrectInput() {
        ConsoleReader mockedConsoleReader = mock(ConsoleReader.class);
        ConsoleController consoleController = new ConsoleController(mockedConsoleReader);

        when(mockedConsoleReader.readNextLineFromConsole(anyString())).thenAnswer(new Answer() {
            private int count = 0;

            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                switch (count++) {
                    case 0:
                        return "Incorrect input";

                    case 1:
                        return "1";

                    case 2:
                        return "Tapa";

                    default:
                        return "q";
                }
            }
        });

        consoleController.start();

        verify(mockedConsoleReader, times(4)).readNextLineFromConsole(anyString());
    }


    @Test
    public void testConsoleReaderInvokedFileInput() {
        ConsoleReader mockedConsoleReader = mock(ConsoleReader.class);
        ConsoleController consoleController = new ConsoleController(mockedConsoleReader);

        when(mockedConsoleReader.readNextLineFromConsole(anyString())).thenAnswer(new Answer() {
            private int count = 0;

            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                switch (count++) {
                    case 0:
                        return "2";

                    default:
                        return "q";
                }
            }
        });

        consoleController.start();

        verify(mockedConsoleReader).readNextLineFromConsole(anyString());
    }


    @Test
    public void testFileServiceMethodsInvoked() {

        FileService mockedFileService = mock(FileService.class);
        ConsoleReader mockedConsoleReader = mock(ConsoleReader.class);
        ConsoleController consoleController = new ConsoleController(mockedConsoleReader);
        consoleController.setFileService(mockedFileService);

        String[] exampleFileReadOutput = {"Tallinn", "Tapa", "Tartu"};
        when(mockedFileService.readLinesFromInputFile(anyString())).thenReturn(exampleFileReadOutput);

        when(mockedConsoleReader.readNextLineFromConsole(anyString())).thenAnswer(new Answer() {
            private int count = 0;

            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                switch (count++) {
                    case 0:
                        return "2";

                    default:
                        return "q";
                }
            }
        });

        consoleController.start();
        verify(mockedFileService).readLinesFromInputFile(anyString());

    }

    @Test
    public void testConsoleReaderInvoked3TimesInputCorrect() {
        ConsoleReader mockedConsoleReader = mock(ConsoleReader.class);
        ConsoleController consoleController = new ConsoleController(mockedConsoleReader);

        when(mockedConsoleReader.readNextLineFromConsole(anyString())).thenAnswer(new Answer() {
            private int count = 0;

            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                switch (count++) {
                    case 0:
                        return "1";

                    case 1:
                        return "Tallinn";

                    default:
                        return "q";
                }
            }
        });

        consoleController.start();

        verify(mockedConsoleReader, times(3)).readNextLineFromConsole(anyString());
    }

    @Test
    public void testConsoleReaderInvoked5TimesInputCorrect() {
        ConsoleReader mockedConsoleReader = mock(ConsoleReader.class);
        ConsoleController consoleController = new ConsoleController(mockedConsoleReader);


        when(mockedConsoleReader.readNextLineFromConsole(anyString())).thenAnswer(new Answer() {
            private int count = 0;

            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                switch (count++) {
                    case 0:
                        return "1";

                    case 1:
                        return "Tallinn";

                    case 2:
                        return "Tapa";

                    case 3:
                        return "Tartu";

                    default:
                        return "q";
                }
            }
        });

        consoleController.start();

        verify(mockedConsoleReader, times(5)).readNextLineFromConsole(anyString());
    }
}
