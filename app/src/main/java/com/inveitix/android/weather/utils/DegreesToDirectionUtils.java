package com.inveitix.android.weather.utils;

public class DegreesToDirectionUtils {
    private static String[] DIRECTIONS =
            {"N","NNE","NE","ENE","E","ESE", "SE", "SSE","S","SSW","SW","WSW","W","WNW","NW","NNW"};

    public String getDirectionByDeg(double deg){
        int val = (int) ((deg/22.5)+.5);

        return DIRECTIONS[val % DIRECTIONS.length];
    }
}
