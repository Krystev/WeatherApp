package com.inveitix.android.weather.ui;

import android.app.ProgressDialog;
import android.content.Intent;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import com.inveitix.android.weather.R;
import com.inveitix.android.weather.constants.IntentConstants;
import com.inveitix.android.weather.utils.PermissionsUtils;

import butterknife.OnClick;

public class MainActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private ProgressDialog dialog;

    @Override
    protected void onViewCreated() {

        showProgress();
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
        hideProgress();
        this.map = googleMap;

    }

    private void hideProgress() {
        dialog.dismiss();
    }

    @OnClick(R.id.btn_choose)
    public void chooseLocation(){
        showWeather();
    }

    private void showWeather() {
        Intent weatherIntent = new Intent(this, WeatherActivity.class);
        weatherIntent.putExtra(IntentConstants.LAT, getLocationUnderX().latitude);
        weatherIntent.putExtra(IntentConstants.LONG, getLocationUnderX().longitude);
        startActivity(weatherIntent);
    }

    public LatLng getLocationUnderX(){
        return map.getCameraPosition().target;
    }

    private void initProgress() {
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(getString(R.string.loading));
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    private void showProgress(){
        initProgress();
    }
}
