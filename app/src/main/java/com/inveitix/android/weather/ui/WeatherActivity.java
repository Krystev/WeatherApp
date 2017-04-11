package com.inveitix.android.weather.ui;

import android.util.Log;
import android.widget.TextView;

import com.github.pwittchen.weathericonview.WeatherIconView;
import com.inveitix.android.weather.R;
import com.inveitix.android.weather.data.models.WeatherResponse;
import com.inveitix.android.weather.usecases.WeatherUsecase;
import com.inveitix.android.weather.utils.ProgressUtils;

import java.util.Locale;

import butterknife.BindView;

public class WeatherActivity extends BaseActivity implements WeatherUsecase.ViewListener {
    public static final int DEFAULT_VALUE = 0;
    public static final int CURRENT_INDEX = 0;
    private WeatherUsecase usecase;
    private ProgressUtils progressUtils;

    @BindView(R.id.txt_town) TextView txtTown;
    @BindView(R.id.txt_current_temp) TextView txtCurrTemp;
    @BindView(R.id.txt_humidity) TextView txtHumidity;
    @BindView(R.id.txt_pressure) TextView txtPressure;
    @BindView(R.id.txt_wind_speed) TextView txtWindSpeed;
    @BindView(R.id.txt_wind_deg) TextView txtWindDirection;
    @BindView(R.id.txt_weather_type) TextView txtWeatherType;
    @BindView(R.id.txt_weather_description) TextView txtWeatherDescription;
    @BindView(R.id.icon_weather) WeatherIconView weatherIconView;


    @Override
    protected void onViewCreated() {
        setToolBarAndUpNavigation();
        this.usecase = new WeatherUsecase(this);
        this.progressUtils = new ProgressUtils();

        double lat = getIntent().getDoubleExtra(MainActivity.LAT, DEFAULT_VALUE);
        double lon = getIntent().getDoubleExtra(MainActivity.LON, DEFAULT_VALUE);

        usecase.onUiReady(lat, lon);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_weather;
    }

    @Override
    public void showProgress() {
        progressUtils.showProgress(this);
    }

    @Override
    public void hideProgress() {
        progressUtils.hideProgress();
    }

    @Override
    public void showWeather(WeatherResponse weather) {
        txtTown.setText(weather.getName());
        txtCurrTemp.setText(String.format("%1$,.0f°C", weather.getMain().getTemp()));
        txtHumidity.setText(String.format("%1$,.0f%%", weather.getMain().getHumidity()));
        txtPressure.setText(String.format("%1$,.0f hPa", weather.getMain().getPressure()));
        txtWindSpeed.setText(String.format("%1$,.0f km/h", weather.getWind().getSpeed()));
        txtWindDirection.setText(String.format("%s", weather.getWind().getDirection()));
        txtWeatherType.setText(weather.getWeather().get(CURRENT_INDEX).getMain());
        txtWeatherDescription.setText(weather.getWeather().get(CURRENT_INDEX).getDescription());
    }
}
