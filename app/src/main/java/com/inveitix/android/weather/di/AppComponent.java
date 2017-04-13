package com.inveitix.android.weather.di;

import com.inveitix.android.weather.ui.BaseActivity;
import com.inveitix.android.weather.ui.MapActivity;
import com.inveitix.android.weather.ui.WeatherActivity;
import com.inveitix.android.weather.usecases.MapUsecase;
import com.inveitix.android.weather.usecases.WeatherUsecase;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class
})
public interface AppComponent {

    void inject(WeatherActivity target);
    void inject(MapActivity target);
    void inject(BaseActivity target);
    void inject(WeatherUsecase target);
    void inject(MapUsecase target);
}
