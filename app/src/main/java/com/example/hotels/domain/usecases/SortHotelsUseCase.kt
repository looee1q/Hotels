package com.example.hotels.domain.usecases

import com.example.hotels.domain.models.SortingType
import com.example.hotels.domain.models.HotelInfo

interface SortHotelsUseCase {

    operator fun invoke(
        hotels: List<HotelInfo>,
        sortingType: SortingType
    ): List<HotelInfo>
}