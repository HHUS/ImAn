package com.csii.model;

import com.csii.application.ImanApplication;
import com.csii.common.BaseModel;
import com.csii.common.Constants;
import com.csii.common.presenter.ModelCallback;
import com.csii.common.presenter.WeatherCallBack;
import com.csii.entity.WeatherEntity;
import com.csii.network.api.AppHttpClient;
import com.csii.network.api.WeatherApi;
import com.csii.utils.PreferencesUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sunhao on 2017/3/29.
 */

public class WeatherModel extends BaseModel {

    private WeatherApi mWeatherApiService;

    private WeatherEntity mCachedWeather;


    @Override
    public void onCreate() {
        super.onCreate();

        mWeatherApiService = AppHttpClient.getInstance().getService(WeatherApi.class);


        mCachedWeather = initWeather();

    }


    private WeatherEntity initWeather() {

        WeatherEntity weatherEntity = null;
        String mainPageCache = PreferencesUtil.get(Constants.MAIN_PAGE_WEATHER, Constants.DEFAULT_STR);

        if (!mainPageCache.equals(Constants.DEFAULT_STR)) {
            weatherEntity = ImanApplication.getGson().fromJson(mainPageCache, WeatherEntity.class);
        }

        return weatherEntity;
    }

    public void updateDefaultWeather() {

    }

    public void updateWeather(final String cityId, final ModelCallback.WeatherResult reultCallback, final WeatherCallBack.NotificationStatus notifiCallback) {
        Call<WeatherEntity> weatherEntityCall = mWeatherApiService.getWeather(cityId);

        weatherEntityCall.enqueue(new Callback<WeatherEntity>() {
            @Override
            public void onResponse(Call<WeatherEntity> call, Response<WeatherEntity> response) {
                WeatherEntity weatherEntity = response.body();
                if (response.isSuccessful()) {
                    String cache = ImanApplication.getGson().toJson(weatherEntity);
                    PreferencesUtil.put(Constants.MAIN_PAGE_WEATHER, cache);
                    mCachedWeather = weatherEntity;

                    reultCallback.onWeather(weatherEntity);
                    notifiCallback.onUpdateNotification();

                }
            }

            @Override
            public void onFailure(Call<WeatherEntity> call, Throwable t) {

            }
        });
    }


}
