package com.mlr_apps.weather.domain.weather


data class WeatherInfo(
    val timeZone: String,
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)
