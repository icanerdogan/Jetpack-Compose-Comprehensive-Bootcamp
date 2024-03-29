package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app7weather.network

import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.BuildConfig
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app7weather.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = BuildConfig.WEATHER_API_KEY // your api key
    ): Weather
}