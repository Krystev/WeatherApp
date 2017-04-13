package com.inveitix.android.weather.di;

import android.location.LocationManager;
import android.view.LayoutInflater;

import com.inveitix.android.weather.App;
import com.inveitix.android.weather.data.remote.WeatherServiceRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.LOCATION_SERVICE;

@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    App provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(app);
    }

    @Provides
    @Singleton
    Executor provideExecutor() {
        return Executors.newCachedThreadPool();
    }

    @Provides
    @Singleton
    WeatherServiceRepository provideWeatherService() {
        return new WeatherServiceRepository();
    }

    @Provides @Singleton LocationManager provideLocationManager() {
        return (LocationManager) app.getSystemService(LOCATION_SERVICE);
    }
}