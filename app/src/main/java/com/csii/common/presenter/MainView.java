package com.csii.common.presenter;

import com.csii.common.BaseView;
import com.csii.entity.WeatherEntity;
import com.csii.ui.adapter.HoursForecastData;

import java.util.List;

/**
 * Created by sunhao on 2017/3/29.
 */

public interface MainView extends BaseView{

    void onBasicInfo(WeatherEntity.BasicEntity basicEntity, List<HoursForecastData> hoursForecastDatas,boolean isLocationCity);


//    void onMoreInfo(AqiData aqiData, List<DailyWeatherData> dailyForecastDatas, LifeIndexData lifeIndexData);

    void onRefreshing(boolean refreshing);
}
