package http_service_test;

import http_service.LinkGenerator;
import org.junit.Test;
import static org.junit.Assert.*;

public class UrlGenerationTest {

    @Test
    public void generateCurrentWeatherLinkByCityTest() {
        String cityName = "Tallinn";
        String link = LinkGenerator.generateWeatherLinkByCity(cityName);
        assertEquals("http://api.openweathermap.org/data/2.5/weather?q=Tallinn&APPID=b23a28015112de497b276e514d2d04a0", link);
    }

    @Test
    public void generateForecastLinkByCityTest() {
        String cityName = "Tallinn";
        String link = LinkGenerator.generateForecastLinkByCity(cityName);
        assertEquals("http://api.openweathermap.org/data/2.5/forecast?q=Tallinn&APPID=b23a28015112de497b276e514d2d04a0", link);
    }

}
