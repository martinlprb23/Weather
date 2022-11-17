package com.mlr_apps.weather.presentation

import com.mlr_apps.weather.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo : WeatherInfo? = null,
    val isLoading : Boolean = false,
    val error: String? = null
)
