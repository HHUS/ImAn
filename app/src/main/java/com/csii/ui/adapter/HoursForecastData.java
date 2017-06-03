package com.csii.ui.adapter;


import com.csii.entity.WeatherEntity;
import com.csii.iman.R;

/**
 * Created by SilenceDut on 16/10/29.
 */

public class HoursForecastData implements BaseAdapterData {

    public WeatherEntity.HoursForecastEntity hoursForecastData;

    public HoursForecastData(WeatherEntity.HoursForecastEntity hoursForecastData) {
        this.hoursForecastData = hoursForecastData;
    }

    @Override
    public int getItemViewType() {
        return R.layout.item_hour_forecast;
    }
}
