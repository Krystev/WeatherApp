package com.inveitix.android.weather.usecases;

import android.support.annotation.NonNull;

import com.inveitix.android.weather.data.models.WeatherResponse;
import com.inveitix.android.weather.data.remote.WeatherServiceRepository;
import com.inveitix.android.weather.repositories.WeatherRepository;
import com.inveitix.android.weather.utils.DegreesToDirectionUtils;
import com.inveitix.android.weather.utils.IconParser;

import javax.inject.Inject;

public class WeatherUsecase {

    private static final int CURRENT_INDEX = 0;

    private DegreesToDirectionUtils degToDirection;
    private ViewListener viewListener;
    private WeatherServiceRepository weatherService;
    private IconParser iconParser;

    @Inject
    WeatherUsecase(WeatherServiceRepository weatherService) {
        this.degToDirection = new DegreesToDirectionUtils();
        this.iconParser = new IconParser();
        this.weatherService = weatherService;
    }

    public void setListener(ViewListener viewListener) {
        this.viewListener = viewListener;
    }

    public void onUiReady(double lat, double lon) {
        getCurrentWeather(lat, lon);
    }

    private void getCurrentWeather(double lat, double lon) {
        viewListener.showProgress();
        weatherService.getCurrentWeather(getWeatherReceivedListener(), lat, lon);
    }

    @NonNull
    private WeatherRepository.OnWeatherReceivedListener getWeatherReceivedListener() {
        return new WeatherRepository.OnWeatherReceivedListener() {
            @Override
            public void onWeatherReceived(WeatherResponse weather) {
                formatData(weather);
                viewListener.showWeather(weather);
                viewListener.hideProgress();
            }
        };
    }

    private void formatData(WeatherResponse weather) {
        int formattedIcon =
                iconParser.getFormattedIcon(weather.getWeather().get(CURRENT_INDEX).getIcon());
        weather.getWeather().get(CURRENT_INDEX).setFormattedIcon(formattedIcon);
        weather.getWind().setDirection(
                degToDirection.getDirectionByDeg(weather.getWind().getDeg()));
        weather.getMain().setTemp(convertKelvinToCel(weather.getMain().getTemp()));
        weather.getMain().setTempMin(convertKelvinToCel(weather.getMain().getTempMin()));
        weather.getMain().setTempMax(convertKelvinToCel(weather.getMain().getTempMax()));
    }

    private double convertKelvinToCel(double kel) {
        return kel - 273.15;
    }

    public interface ViewListener {
        void showProgress();
        void hideProgress();
        void showWeather(WeatherResponse weather);
    }
}
