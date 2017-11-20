package model.open_weather.current;

import com.google.gson.annotations.SerializedName;
import model.APIModel;

import static constants.Constants.KELVIN_TO_CELSIUS;

public class OpenWeatherCurrentWeatherModel implements APIModel {

    @SerializedName("name")
    private String locationName;

    //private String weatherMain;
    //private String weatherDescription;

    private WeatherMain main;

    public double getTemperature() {
        return this.main.getTemp();
    }

    public double getTemperatureInCelsius() {
        return this.main.getTemp() - KELVIN_TO_CELSIUS;
    }

    public double getHumidity() {
        return this.main.getHumidity();
    }

    public String getLocationName() {
        return locationName;
    }

    @Override
    public String toString() {
        return String.format("Current Weather in %s:\n\tTemperature: %.2f\n\tHumidity: %s",
                this.getLocationName(), this.getTemperatureInCelsius(), this.getHumidity());
    }
}
