package com.example.hotels.domain.usecases.impl

import com.example.hotels.domain.models.ApiError
import com.example.hotels.domain.models.ApiResult
import com.example.hotels.domain.models.HotelInfo
import com.example.hotels.domain.repository.HotelsRepository
import com.example.hotels.domain.usecases.GetHotelsUseCase

class GetHotelsUseCaseImpl(
    private val hotelsRepository: HotelsRepository
) : GetHotelsUseCase {

    override suspend operator fun invoke(): ApiResult<List<HotelInfo>, ApiError> {
        return hotelsRepository.getHotels()
    }
}