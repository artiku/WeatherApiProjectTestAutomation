package model.open_weather.forecast;

import com.google.gson.internal.LinkedTreeMap;

import java.util.HashMap;

import static constants.Constants.KELVIN_TO_CELSIUS;

class ForecastDay {

    private double tempMax;
    private double tempMin;

    public ForecastDay(HashMap<String, Object> jsonDay) {

        LinkedTreeMap<String, Double> dayMain = (LinkedTreeMap<String, Double>) jsonDay.get("main");
        tempMax = dayMain.get("temp_max");
        tempMin = dayMain.get("temp_min");
    }

    public double getTempMaxCelsius() {
        return tempMax - KELVIN_TO_CELSIUS;
    }

    public double getTempMinCelsius() {
        return tempMin - KELVIN_TO_CELSIUS;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }
}
