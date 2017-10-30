package http_service;

public class LinkGenerator {

    private static final String API_WEATHER_BASE_LINK = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String API_FORECAST_LINK = "http://api.openweathermap.org/data/2.5/feather?q=";
    private static final String API_KEY = "&APPID=b23a28015112de497b276e514d2d04a0";

    public static String generateWeatherLinkByCoord(String coord) {
        return API_WEATHER_BASE_LINK
                + coord
                + API_KEY;
    }

    public static String linkForTest() {
        return "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";
    }

    public static String generateWeatherLinkByCity(String city) {
        return API_WEATHER_BASE_LINK
                + city
                + API_KEY;
    }

    public static String generateForecastLinkByCity(String city) {
        return API_FORECAST_LINK
                + city
                + API_KEY;
    }
}
