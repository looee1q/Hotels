package com.example.hotels.domain.usecases.impl

import com.example.hotels.domain.models.ApiError
import com.example.hotels.domain.models.ApiResult
import com.example.hotels.domain.models.HotelDetails
import com.example.hotels.domain.repository.HotelsRepository
import com.example.hotels.domain.usecases.GetHotelDetailsByIdUseCase

class GetHotelDetailsByIdUseCaseImpl(
    private val hotelsRepository: HotelsRepository
) : GetHotelDetailsByIdUseCase {

    override suspend operator fun invoke(hotelId: Int): ApiResult<HotelDetails, ApiError> {
        return hotelsRepository.getHotelDetails(hotelId)
    }
}