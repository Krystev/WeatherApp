package com.inveitix.android.weather.di;

import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;

import com.inveitix.android.weather.App;
import com.inveitix.android.weather.data.remote.WeatherServiceRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private App app;

    public AppModule(App app) { this.app = app; }

    @Provides
    @Singleton
    public App provideApplication() { return app; }

    @Provides
    @Singleton
    public LayoutInflater provideLayoutInflater() { return LayoutInflater.from(app); }

    @Provides
    @Singleton
    public Executor provideExecutor() {
        return Executors.newCachedThreadPool();
    }

    @Provides
    @Singleton
    public WeatherServiceRepository provideWeatherService () {
        return new WeatherServiceRepository();
    }

    @Provides
    @Singleton
    public BluetoothManager provideBluetoothManager(App app) {
        return (BluetoothManager) app.getSystemService(Context.BLUETOOTH_SERVICE);
    }
}