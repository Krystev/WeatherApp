package com.inveitix.android.weather.ui;

import android.content.Intent;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import com.inveitix.android.weather.R;
import com.inveitix.android.weather.utils.PermissionsUtils;

import butterknife.OnClick;

public class MainActivity extends BaseActivity implements OnMapReadyCallback {

    public static final String LAT = "lat";
    public static final String LON = "lon";

    private GoogleMap map;

    @Override
    protected void onViewCreated() {
        PermissionsUtils permissionsUtils = new PermissionsUtils();
        permissionsUtils.checkPermissions(this);

        setUpMap();
    }

    private void setUpMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;

    }

    @OnClick(R.id.btn_choose)
    public void chooseLocation(){
        showWeather();
    }

    private void showWeather() {
        Intent weatherIntent = new Intent(this, WeatherActivity.class);
        weatherIntent.putExtra(LAT, getLocationUnderX().latitude);
        weatherIntent.putExtra(LON, getLocationUnderX().longitude);
        startActivity(weatherIntent);
    }

    public LatLng getLocationUnderX(){
        return map.getCameraPosition().target;
    }


}
