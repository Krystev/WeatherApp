package com.inveitix.android.weather.data.remote;


import android.util.Log;

import com.inveitix.android.weather.data.models.WeatherResponse;
import com.inveitix.android.weather.repositories.WeatherRepository;
import com.inveitix.android.weather.utils.DegreesToDirectionUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherServiceRepository implements WeatherRepository {

    private static final String BASE_API_URL = "http://api.openweathermap.org/";
    private static final String WEATHER_API_URL
            = "/data/2.5/weather?id=524901&APPID=813f06edb0e4232284759b7e87e4a265";

    private static String TAG = "WeatherServiceRepo";
    private static WeatherServiceRepository instance;

    private OnWeatherReceivedListener weatherReceivedListener;
    private DegreesToDirectionUtils degToDirection;

    private WeatherServiceRepository() {
        this.degToDirection = new DegreesToDirectionUtils();
    }

    public static WeatherServiceRepository getInstance() {
        if (instance == null) {
            instance = new WeatherServiceRepository();
        }
        return instance;
    }

    @Override
    public void getCurrentWeather(OnWeatherReceivedListener weatherReceivedListener,
                                  double lat, double lon) {
        this.weatherReceivedListener = weatherReceivedListener;
        getWeather(lat, lon);
    }

    private void getWeather(double lat, double lon) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> weather = service.listStats(lat, lon);
        weather.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weather = response.body();
                    weather.getWind().setDirection(
                            degToDirection.getDirectionByDeg(weather.getWind().getDeg()));
                    Log.e(TAG, weather.getMain().getTemp() + " " + weather.getMain().getTempMin() + " "
                    + weather.getMain().getTempMax());
                    weather.getMain().setTemp(convertKelvinToCel(weather.getMain().getTemp()));
                    weather.getMain().setTempMin(convertKelvinToCel(weather.getMain().getTempMin()));
                    weather.getMain().setTempMax(convertKelvinToCel(weather.getMain().getTempMax()));

                    weatherReceivedListener.onWeatherReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

    }

    private double convertKelvinToCel(double kel){
        return kel - 273.15;
    }

    interface WeatherService {
        @GET(WEATHER_API_URL)
        Call<WeatherResponse> listStats(@Query("lat") double lat,
                                        @Query("lon") double lon);
    }
}