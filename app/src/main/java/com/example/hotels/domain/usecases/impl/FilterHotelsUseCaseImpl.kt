package com.example.hotels.domain.usecases.impl

import com.example.hotels.domain.models.FilterType
import com.example.hotels.domain.models.HotelInfo
import com.example.hotels.domain.usecases.FilterHotelsUseCase

class FilterHotelsUseCaseImpl : FilterHotelsUseCase {

    override operator fun invoke(
        hotels: List<HotelInfo>,
        filterType: FilterType
    ): List<HotelInfo> {
        return when (filterType) {
            FilterType.DISTANCE -> hotels.sortedBy { it.distance }
            FilterType.SUITES_AVAILABILITY_COUNT -> hotels.sortedBy { it.suitesAvailability.size }
        }
    }
}