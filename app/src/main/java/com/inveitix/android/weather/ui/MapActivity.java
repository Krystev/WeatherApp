package com.inveitix.android.weather.ui;

import android.content.Intent;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import com.inveitix.android.weather.R;
import com.inveitix.android.weather.di.AppComponent;
import com.inveitix.android.weather.usecases.MapUsecase;
import com.inveitix.android.weather.utils.PermissionsUtils;

import javax.inject.Inject;

import butterknife.OnClick;

public class MapActivity extends BaseActivity implements OnMapReadyCallback, MapUsecase.ViewListener {

    public static final String LAT = "lat";
    public static final String LON = "lon";

    private GoogleMap map;
    @Inject MapUsecase usecase;

    @Override
    protected void doInject(AppComponent component) {
        component.inject(this);
    }

    @Override
    protected void onViewCreated() {
        PermissionsUtils permissionsUtils = new PermissionsUtils(this);
        permissionsUtils.checkPermissions();
        usecase.setListener(this);
        usecase.onUiReady();
    }

    @Override
    public void setUpMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void showWeather() {
        openWeatherActivity();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_map;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
    }

    @OnClick(R.id.btn_choose)
    public void chooseLocation(){
        usecase.showWeather();
    }

    private void openWeatherActivity() {
        Intent weatherIntent = new Intent(this, WeatherActivity.class);
        weatherIntent.putExtra(LAT, getLocationUnderX().latitude);
        weatherIntent.putExtra(LON, getLocationUnderX().longitude);
        startActivity(weatherIntent);
    }

    public LatLng getLocationUnderX(){
        return map.getCameraPosition().target;
    }
}
