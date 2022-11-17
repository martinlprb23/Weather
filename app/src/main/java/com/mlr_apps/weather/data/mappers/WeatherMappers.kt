package com.mlr_apps.weather.data.mappers

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.mlr_apps.weather.data.remote.WeatherDataDto
import com.mlr_apps.weather.data.remote.WeatherDto
import com.mlr_apps.weather.domain.weather.WeatherData
import com.mlr_apps.weather.domain.weather.WeatherInfo
import com.mlr_apps.weather.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val pressure = pressures[index]
        val windSpeed = windSpeeds[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    Log.i("Hour", timeZone)
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
        // TODO: Case when 12 hours
    }
    return WeatherInfo(
        timeZone = timeZone,
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}