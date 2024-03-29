package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app7weather.model

import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app7weather.model.FeelsLike
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app7weather.model.Temp

data class WeatherItem(
    val clouds: Int,
    val deg: Int,
    val dt: Int,
    val feels_like: FeelsLike,
    val gust: Double,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val weather: List<WeatherObject>
                      )