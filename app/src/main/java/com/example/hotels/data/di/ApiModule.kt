package com.example.hotels.data.di

import com.example.hotels.data.api.HotelsApi
import com.example.hotels.data.api.HotelsApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {

    @Binds
    @Singleton
    abstract fun bindsHotelsApi(impl: HotelsApiImpl): HotelsApi
}
