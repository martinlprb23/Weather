package com.mlr_apps.weather.data.remote

import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = "timezone")
    val timeZone: String,
    @field:Json(name="hourly")
    val weatherData: WeatherDataDto
)
