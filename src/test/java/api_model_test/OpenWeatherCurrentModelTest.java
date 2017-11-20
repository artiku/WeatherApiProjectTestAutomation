package api_model_test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.open_weather.current.OpenWeatherCurrentWeatherModel;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class OpenWeatherCurrentModelTest {

    private static final String JSON_EXAMPLE = "{\"main\":{\"temp\":2007.81,\"humidity\":67.7}}";

    @Test
    public void testGsonParsingToObject() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        OpenWeatherCurrentWeatherModel currentWeatherModel = gson.fromJson(JSON_EXAMPLE, OpenWeatherCurrentWeatherModel.class);
        assertEquals(2007.81, currentWeatherModel.getTemperature(), 2);
    }

    @Test
    public void testOWModelCelsiusConvertage() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        OpenWeatherCurrentWeatherModel currentWeatherModel = gson.fromJson(JSON_EXAMPLE, OpenWeatherCurrentWeatherModel.class);
        assertEquals(1734.81, currentWeatherModel.getTemperatureInCelsius(), 2);
    }
}
