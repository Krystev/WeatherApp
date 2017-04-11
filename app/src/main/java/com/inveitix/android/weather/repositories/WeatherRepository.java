package com.inveitix.android.weather.repositories;

import com.inveitix.android.weather.data.models.WeatherResponse;

public interface WeatherRepository {

    void getCurrentWeather(OnWeatherReceivedListener weatherReceivedListener,
                           double lat, double lon);

    interface OnWeatherReceivedListener {
        void onWeatherReceived(WeatherResponse weather);
    }
}
