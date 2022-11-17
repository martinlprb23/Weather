package com.mlr_apps.weather.domain.repository

import com.mlr_apps.weather.domain.util.Resource
import com.mlr_apps.weather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}