package com.example.hotels.domain.repository

import com.example.hotels.domain.models.ApiError
import com.example.hotels.domain.models.ApiResult
import com.example.hotels.domain.models.HotelDetails
import com.example.hotels.domain.models.HotelInfo

interface HotelsRepository {

    suspend fun getHotels(): ApiResult<List<HotelInfo>, ApiError>

    suspend fun getHotelDetails(hotelId: Int): ApiResult<HotelDetails, ApiError>
}