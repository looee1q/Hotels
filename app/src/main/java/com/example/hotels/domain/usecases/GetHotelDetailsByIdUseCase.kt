package com.example.hotels.domain.usecases

import com.example.hotels.domain.models.ApiError
import com.example.hotels.domain.models.ApiResult
import com.example.hotels.domain.models.HotelDetails

interface GetHotelDetailsByIdUseCase {

    suspend operator fun invoke(hotelId: Int): ApiResult<HotelDetails, ApiError>
}