package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app7weather.model

import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app7weather.model.City

data class Weather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherItem>,
    val message: Double
)