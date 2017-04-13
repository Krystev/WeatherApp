package com.inveitix.android.weather.usecases;

import javax.inject.Inject;

public class MapUsecase {

    private ViewListener viewListener;

    @Inject
    MapUsecase(){
    }

    public void onUiReady() {
        viewListener.checkPermissions();
        viewListener.setUpMap();
    }

    public void showWeather(double latitude, double longitude){
        viewListener.onLocationSelected(latitude, longitude);
    }

    public void setListener(ViewListener viewListener) {
        this.viewListener = viewListener;
    }

    public interface ViewListener {
        void setUpMap();
        void onLocationSelected(double latitude, double longitude);
        void checkPermissions();
    }
}
