package com.inveitix.android.weather.di;

import com.inveitix.android.weather.ui.BaseActivity;
import com.inveitix.android.weather.ui.WeatherActivity;
import com.inveitix.android.weather.usecases.WeatherUsecase;
import com.inveitix.android.weather.utils.ProgressUtils;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class
})
public interface AppComponent {

    void inject(WeatherActivity target);
    void inject(BaseActivity target);
    void inject(ProgressUtils target);
    void inject(WeatherUsecase target);
}
