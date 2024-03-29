package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app7weather.repository

import android.util.Log
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app7weather.data.DataOrException
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app7weather.model.Weather
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app7weather.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(cityQuery: String, units: String)
    : DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery, units = units)

        }catch (e: Exception){
            Log.d("REX", "getWeather: $e")
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getWeather: $response")
        return  DataOrException(data = response)

    }

}