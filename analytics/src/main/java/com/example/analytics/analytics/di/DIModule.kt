package com.example.analytics.di

import com.example.analytics.impl.AnalyticsRepoImpl
import com.example.analytics.repo.AnalyticsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DIModule {
    @Binds
    abstract fun bindAnalyticsRepository(impl: AnalyticsRepoImpl): AnalyticsRepo
}