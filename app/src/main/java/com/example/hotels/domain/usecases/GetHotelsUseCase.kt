package com.example.hotels.domain.usecases

import com.example.hotels.domain.models.ApiError
import com.example.hotels.domain.models.ApiResult
import com.example.hotels.domain.models.HotelInfo

interface GetHotelsUseCase {

    suspend operator fun invoke(): ApiResult<List<HotelInfo>, ApiError>
}