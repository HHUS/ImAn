package com.csii.common.presenter;

import com.csii.common.BasePresenter;
import com.csii.common.BaseView;
import com.csii.entity.WeatherEntity;
import com.csii.model.WeatherModel;
import com.csii.ui.adapter.HoursForecastData;
import com.csii.utils.ModelManager;
import com.csii.utils.TaskExecutor;
import com.squareup.haha.perflib.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunhao on 2017/3/29.
 */

public class WeatherPresenter extends BasePresenter<MainView> implements ModelCallback.LocationResult, ModelCallback.WeatherResult {

    public MainView mMainView;
    private WeatherModel mWeatherModel;

    public WeatherPresenter(MainView mMainView) {
        super(mMainView);

         mMainView = mMainView;
        mWeatherModel = ModelManager.getModel(WeatherModel.class);

        TaskExecutor.executeTask(new Runnable() {
            @Override
            public void run() {

            }
        });

    }


    public void getDefaultWeather(){
//        WeatherEntity weatherEntity = mWeatherModel.getCachedWeather();

    }

    public void getWeather(final String cityId){
        mMainView.onRefreshing(true);
        mWeatherModel.updateWeather(cityId,this,null);
    }

    @Override
    public void onLocationComplete(String cityId, boolean success) {

    }

    @Override
    public void onWeather(WeatherEntity weatherEntity) {
        //拿到数据，利用mainView将数据返回
        if(weatherEntity == null){
            mMainView.onRefreshing(false);
        }else{
            //将数据解析出来，利用view返回去
            WeatherEntity.BasicEntity basicEntity = weatherEntity.getBasic();
            List<HoursForecastData> hoursForecastDatas = new ArrayList<>();

            for (WeatherEntity.HoursForecastEntity hoursForecastEntit:weatherEntity.getHoursForecast()){

            }
        }



    }
}
