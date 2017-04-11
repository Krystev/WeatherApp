package com.inveitix.android.weather.usecases;

import android.support.annotation.NonNull;

import com.inveitix.android.weather.data.models.WeatherResponse;
import com.inveitix.android.weather.data.remote.WeatherServiceRepository;
import com.inveitix.android.weather.repositories.WeatherRepository;
import com.inveitix.android.weather.utils.DegreesToDirectionUtils;
import com.inveitix.android.weather.utils.IconParser;

public class WeatherUsecase {

    private DegreesToDirectionUtils degToDirection;
    private ViewListener viewListener;
    private WeatherServiceRepository weatherService;
    private IconParser iconParser;

    public WeatherUsecase(ViewListener viewListener) {
        this.degToDirection = new DegreesToDirectionUtils();
        this.iconParser = new IconParser();
        this.viewListener = viewListener;
        this.weatherService = WeatherServiceRepository.getInstance();
    }

    public void onUiReady(double lat, double lon) {
        viewListener.showProgress();
        getCurrentWeather(lat, lon);
    }

    private void getCurrentWeather(double lat, double lon) {
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
        int formattedIcon = iconParser.getFormattedIcon(weather.getWeather().get(0).getIcon());
        weather.getWeather().get(0).setFormattedIcon(formattedIcon);
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
