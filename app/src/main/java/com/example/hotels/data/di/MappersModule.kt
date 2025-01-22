package com.example.hotels.data.di

import com.example.hotels.data.model.dto.HotelDetailsDto
import com.example.hotels.data.model.dto.HotelInfoDto
import com.example.hotels.data.model.mappers.HotelDetailsMapper
import com.example.hotels.data.model.mappers.HotelInfoMapper
import com.example.hotels.data.model.mappers.Mapper
import com.example.hotels.domain.models.HotelDetails
import com.example.hotels.domain.models.HotelInfo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MappersModule {

    @Binds
    abstract fun bindsHotelDetailsMapper(
        impl: HotelDetailsMapper
    ): Mapper<HotelDetailsDto, HotelDetails>

    @Binds
    abstract fun bindsHotelInfoMapper(impl: HotelInfoMapper): Mapper<HotelInfoDto, HotelInfo>
}
