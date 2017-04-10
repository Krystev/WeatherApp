package com.inveitix.android.weather.ui;

import com.inveitix.android.weather.R;

public class WeatherActivity extends BaseActivity {

    @Override
    protected void onViewCreated() {
        setToolBarAndUpNavigation();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_weather;
    }
}
