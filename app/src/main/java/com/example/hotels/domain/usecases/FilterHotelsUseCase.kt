package com.example.hotels.domain.usecases

import com.example.hotels.domain.models.FilterType
import com.example.hotels.domain.models.HotelInfo

interface FilterHotelsUseCase {

    operator fun invoke(
        hotels: List<HotelInfo>,
        filterType: FilterType
    ): List<HotelInfo>
}