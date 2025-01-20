package com.example.hotels.data.api

import com.example.hotels.data.model.dto.HotelDetailsDto
import com.example.hotels.data.model.dto.HotelInfoDto

interface HotelsApi {

    suspend fun getHotelsList(): List<HotelInfoDto>

    suspend fun getHotelInfo(hotelId: Int): HotelDetailsDto
}
