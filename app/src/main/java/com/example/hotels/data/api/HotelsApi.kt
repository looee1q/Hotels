package com.example.hotels.data.api

import com.example.hotels.data.dto.HotelDetailsDto
import com.example.hotels.data.dto.HotelInfoDto

interface HotelsApi {

    suspend fun getHotelsList(): List<HotelInfoDto>

    suspend fun getHotelInfo(hotelId: Int): HotelDetailsDto
}
