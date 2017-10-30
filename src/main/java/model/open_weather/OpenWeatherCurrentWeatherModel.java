package model.open_weather;

import com.google.gson.annotations.SerializedName;
import model.APIModel;

import java.util.HashMap;

public class OpenWeatherCurrentWeatherModel implements APIModel {

    @SerializedName("name")
    private String locationName;

    //private String weatherMain;
    //private String weatherDescription;

    private WeatherMain main;

    public double getTemperature() {
        return this.main.getTemp();
    }

    public double getHumidity() {
        return this.main.getHumidity();
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return "Current Weather in " + this.getLocationName() + ":\n" +
                "\tTemperature: " + this.getTemperature() + "\n" +
                "\tHumidity: " + this.getHumidity();
    }
}
