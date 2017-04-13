package com.inveitix.android.weather;

import android.app.Application;
import android.content.Context;

import com.inveitix.android.weather.di.AppComponent;
import com.inveitix.android.weather.di.AppModule;
import com.inveitix.android.weather.di.DaggerAppComponent;

public class App extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getAppComponent(Context context) {
        return ((App) context.getApplicationContext()).getAppComponent();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
