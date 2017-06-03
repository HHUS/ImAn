package com.csii.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.csii.common.presenter.MainView;
import com.csii.common.presenter.WeatherPresenter;
import com.csii.entity.WeatherEntity;
import com.csii.ui.adapter.HoursForecastData;

import java.util.List;

/**
 * Created by sunhao on 2017/3/30.
 */

public class MvpActivity extends AppCompatActivity implements MainView{


    private WeatherPresenter weatherPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        weatherPresenter = new WeatherPresenter(this);
        weatherPresenter.attachView(this);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            weatherPresenter.getDefaultWeather();
        }
    }

    @Override
    public Context getContext() {
        return null;
    }

    //读取缓存中东西，过期读取网络
    @Override
    public void onBasicInfo(WeatherEntity.BasicEntity basicEntity, List<HoursForecastData> hoursForecastDatas, boolean isLocationCity) {

    }

    @Override
    public void onRefreshing(boolean refreshing) {

    }
}
