package com.inveitix.android.weather.utils;

import com.inveitix.android.weather.R;

public class IconParser {
    public int getFormattedIcon(String icon){
        int iconNum = Integer.parseInt(icon.substring(0, 2));

        switch (iconNum){
            case 1: return R.string.wi_wu_clear;
            case 2: return R.string.wi_day_cloudy;
            case 3: return R.string.wi_cloud;
            case 4: return R.string.wi_cloudy;
            case 9: return R.string.wi_showers;
            case 10: return R.string.wi_day_showers;
            case 11: return R.string.wi_thunderstorm;
            case 13: return R.string.wi_snow;
            case 50: return R.string.wi_fog;
            default: return 0;
        }
    }
}
