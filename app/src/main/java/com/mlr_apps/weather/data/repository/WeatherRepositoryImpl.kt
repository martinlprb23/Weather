package com.mlr_apps.weather.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.mlr_apps.weather.data.mappers.toWeatherInfo
import com.mlr_apps.weather.data.remote.WeatherAPI
import com.mlr_apps.weather.domain.repository.WeatherRepository
import com.mlr_apps.weather.domain.util.Resource
import com.mlr_apps.weather.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherAPI
) : WeatherRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                     lat = lat,
                    long = long,
                    timezone = "auto"
                ).toWeatherInfo()
            )
        }catch (e: Exception ){
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown occurred.")
        }
    }
}