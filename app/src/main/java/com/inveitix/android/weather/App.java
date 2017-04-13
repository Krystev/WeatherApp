package com.inveitix.android.weather;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.inveitix.android.weather.di.AppComponent;
import com.inveitix.android.weather.di.AppModule;
import com.inveitix.android.weather.di.DaggerAppComponent;

public class App extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(getAppModule()).build();
    }

    @NonNull
    private AppModule getAppModule() {
        return new AppModule(this);
    }

    public static AppComponent getAppComponent(Context context) {
        return ((App) context.getApplicationContext()).getAppComponent();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
