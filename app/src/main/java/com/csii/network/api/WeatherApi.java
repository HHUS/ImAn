package com.csii.network.api;

import com.csii.entity.WeatherEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sunhao on 2017/3/29.
 */

public interface WeatherApi {

    String BASE_URL = "http://knowweather.duapp.com";

    @GET("/v1/0/weather/{cityId}")
    Call<WeatherEntity> getWeather(@Path("cityId") String cityId);
}
