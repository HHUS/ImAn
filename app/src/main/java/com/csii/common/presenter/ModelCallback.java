package com.csii.common.presenter;


import com.csii.entity.WeatherEntity;

/**
 * Created by SilenceDut on 2016/11/15 .
 */

public interface ModelCallback {
    interface LocationResult {
        void onLocationComplete(String cityId, boolean success);
    }

    interface WeatherResult {
        void onWeather(WeatherEntity weatherEntity);
    }

}
