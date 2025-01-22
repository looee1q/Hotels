package com.example.hotels.domain.di

import com.example.hotels.domain.usecases.FilterHotelsUseCase
import com.example.hotels.domain.usecases.GetHotelDetailsByIdUseCase
import com.example.hotels.domain.usecases.GetHotelsUseCase
import com.example.hotels.domain.usecases.impl.FilterHotelsUseCaseImpl
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
    abstract fun bindsFilterHotelsUseCase(impl: FilterHotelsUseCaseImpl): FilterHotelsUseCase

    @Binds
    @Singleton
    abstract fun bindsGetHotelDetailsByIdUseCase(impl: GetHotelDetailsByIdUseCaseImpl): GetHotelDetailsByIdUseCase

    @Binds
    @Singleton
    abstract fun bindsGetHotelsUseCase(impl: GetHotelsUseCaseImpl): GetHotelsUseCase
}
