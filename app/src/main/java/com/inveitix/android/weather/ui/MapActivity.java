package com.inveitix.android.weather.ui;

import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.maps.CameraUpdateFactory;
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

    private GoogleMap map;
    @Inject MapUsecase usecase;
    @Inject LocationManager locationManager;

    @Override
    protected void doInject(AppComponent component) {
        component.inject(this);
    }

    @Override
    protected void onViewCreated() {
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
    public void onLocationSelected(double lat, double lon) {
        startActivity(WeatherActivity.getIntent(this,lat, lon));
    }

    @Override
    public void checkPermissions() {
        PermissionsUtils permissionsUtils = new PermissionsUtils(this);
        permissionsUtils.checkPermissions();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_map;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        setCurrentLocation();
    }

    @SuppressWarnings("MissingPermission")
    private void setCurrentLocation() {
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        map.moveCamera(CameraUpdateFactory.newLatLng(
                new LatLng(location.getLatitude(), location.getLongitude())));
    }

    @OnClick(R.id.btn_choose)
    public void chooseLocation(){
        usecase.setLocation(getLocationUnderX().latitude, getLocationUnderX().longitude);
    }

    public LatLng getLocationUnderX(){
        return map.getCameraPosition().target;
    }
}
