package api_model_test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.open_weather.current.OpenWeatherCurrentWeatherModel;
import model.open_weather.forecast.OpenWeatherForecastModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OpenWeatherForecastTest {

    private static final String JSON_EXAMPLE = "{\"list\":[{\"main\":{\"temp_min\":259.086,\"temp_max\":261.45}}," +
            "{\"main\": {\"temp_min\":259.086,\"temp_max\":261.45}},{\"main\":{\"temp_min\":259.086,\"temp_max\":261.45}}]}";

    @Test
    public void testGsonParsingToObject() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        OpenWeatherForecastModel forecastModel = gson.fromJson(JSON_EXAMPLE, OpenWeatherForecastModel.class);
        forecastModel.arrangeThreeDayForecast();
        assertEquals("Min/Max temperature forecast for the next 3 days:\n" +
                "(Min: -13,91 | Max: -11,55)\n" +
                "(Min: -13,91 | Max: -11,55)\n" +
                "(Min: -13,91 | Max: -11,55)\n", forecastModel.toString());
    }
}
