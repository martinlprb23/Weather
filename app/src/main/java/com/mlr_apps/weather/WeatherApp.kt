package com.mlr_apps.weather

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}