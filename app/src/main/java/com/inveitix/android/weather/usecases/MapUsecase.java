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

    public void showWeather(){
        viewListener.onLocationSelected();
    }

    public void setListener(ViewListener viewListener) {
        this.viewListener = viewListener;
    }

    public interface ViewListener {
        void setUpMap();
        void onLocationSelected();
        void checkPermissions();
    }
}
