package com.inveitix.android.weather.ui;

import android.widget.TextView;

import com.github.pwittchen.weathericonview.WeatherIconView;
import com.inveitix.android.weather.R;
import com.inveitix.android.weather.data.models.WeatherResponse;
import com.inveitix.android.weather.di.AppComponent;
import com.inveitix.android.weather.usecases.WeatherUsecase;
import com.inveitix.android.weather.utils.ProgressUtils;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;

public class WeatherActivity extends BaseActivity implements WeatherUsecase.ViewListener {
    public static final int DEFAULT_VALUE = 0;
    public static final int CURRENT_INDEX = 0;

    @Inject WeatherUsecase usecase;
    ProgressUtils progressUtils;

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
    protected void doInject(AppComponent component) {
        component.inject(this);
    }

    @Override
    protected void onViewCreated() {
        usecase.setListener(this);

        progressUtils = new ProgressUtils(this);
        setToolBarAndUpNavigation();

        double lat = getIntent().getDoubleExtra(MapActivity.LAT, DEFAULT_VALUE);
        double lon = getIntent().getDoubleExtra(MapActivity.LON, DEFAULT_VALUE);

        usecase.onUiReady(lat, lon);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_weather;
    }

    @Override
    public void showProgress() {
        progressUtils.showProgress();
    }

    @Override
    public void hideProgress() {
        progressUtils.hideProgress();
    }

    @Override
    public void showWeather(WeatherResponse weather) {

        weatherIconView.setIconResource(
                getString(weather.getWeather().get(CURRENT_INDEX).getFormattedIcon()));
        txtTown.setText(weather.getName());
        txtCurrTemp.setText(String.format(Locale.getDefault(), getString(R.string.temp_format),
                weather.getMain().getTemp()));
        txtHumidity.setText(String.format(Locale.getDefault(), getString(R.string.humidity_format),
                        weather.getMain().getHumidity()));
        txtPressure.setText(String.format(Locale.getDefault(), getString(R.string.pressure_format),
                weather.getMain().getPressure()));
        txtWindSpeed.setText(String.format(Locale.getDefault(), getString(R.string.speed_format),
                weather.getWind().getSpeed()));
        txtWindDirection.setText(weather.getWind().getDirection());
        txtWeatherType.setText(weather.getWeather().get(CURRENT_INDEX).getMain());
        txtWeatherDescription.setText(weather.getWeather().get(CURRENT_INDEX).getDescription());
    }
}
