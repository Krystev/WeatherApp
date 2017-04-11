package com.inveitix.android.weather.usecases;

import android.support.annotation.NonNull;
import android.util.Log;

import com.inveitix.android.weather.data.models.WeatherResponse;
import com.inveitix.android.weather.data.remote.WeatherServiceRepository;
import com.inveitix.android.weather.repositories.WeatherRepository;

public class WeatherUsecase {

    private ViewListener viewListener;
    private WeatherServiceRepository weatherService;

    public WeatherUsecase(ViewListener viewListener) {
        this.viewListener = viewListener;
        this.weatherService = WeatherServiceRepository.getInstance();
    }

    public void onUiReady(double lat, double lon) {
        viewListener.showProgress();
        getCurrentWeather(lat, lon);
    }

    private void getCurrentWeather(double lat, double lon){
        weatherService.getCurrentWeather(getWeatherReceivedListener(), lat, lon);
    }

    @NonNull
    private WeatherRepository.OnWeatherReceivedListener getWeatherReceivedListener() {
        return new WeatherRepository.OnWeatherReceivedListener() {
            @Override
            public void onWeatherReceived(WeatherResponse weather) {
                viewListener.showWeather(weather);
                viewListener.hideProgress();
            }
        };
    }

    public interface ViewListener {

        void showProgress();

        void hideProgress();

        void showWeather(WeatherResponse weather);
    }
}
