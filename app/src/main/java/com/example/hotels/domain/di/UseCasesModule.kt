package com.example.hotels.domain.di

import com.example.hotels.domain.usecases.SortHotelsUseCase
import com.example.hotels.domain.usecases.GetHotelDetailsByIdUseCase
import com.example.hotels.domain.usecases.GetHotelsUseCase
import com.example.hotels.domain.usecases.impl.SortHotelsUseCaseImpl
import com.example.hotels.domain.usecases.impl.GetHotelDetailsByIdUseCaseImpl
import com.example.hotels.domain.usecases.impl.GetHotelsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {

    @Binds
    @Singleton
    abstract fun bindsSortHotelsUseCase(impl: SortHotelsUseCaseImpl): SortHotelsUseCase

    @Binds
    @Singleton
    abstract fun bindsGetHotelDetailsByIdUseCase(impl: GetHotelDetailsByIdUseCaseImpl): GetHotelDetailsByIdUseCase

    @Binds
    @Singleton
    abstract fun bindsGetHotelsUseCase(impl: GetHotelsUseCaseImpl): GetHotelsUseCase
}
