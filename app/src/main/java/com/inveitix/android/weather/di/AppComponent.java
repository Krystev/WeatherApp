package com.inveitix.android.weather.di;

import com.inveitix.android.weather.ui.BaseActivity;
import com.inveitix.android.weather.ui.WeatherActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        //TODO add modules here
})
public interface AppComponent {

    void inject(WeatherActivity target);
    void inject(BaseActivity target);
}
