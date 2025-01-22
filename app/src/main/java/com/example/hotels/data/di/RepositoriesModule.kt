package com.example.hotels.data.di

import com.example.hotels.data.repositoryimpl.HotelsRepositoryImpl
import com.example.hotels.domain.repository.HotelsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    @Singleton
    abstract fun bindsHotelsRepository(impl: HotelsRepositoryImpl): HotelsRepository
}
