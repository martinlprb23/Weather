package com.mlr_apps.weather.di

import com.mlr_apps.weather.data.repository.WeatherRepositoryImpl
import com.mlr_apps.weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherTracker(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}