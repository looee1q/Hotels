package com.example.hotels.domain.usecases.impl

import com.example.hotels.domain.models.SortingType
import com.example.hotels.domain.models.HotelInfo
import com.example.hotels.domain.usecases.SortHotelsUseCase
import javax.inject.Inject

class SortHotelsUseCaseImpl @Inject constructor() : SortHotelsUseCase {

    override operator fun invoke(
        hotels: List<HotelInfo>,
        sortingType: SortingType
    ): List<HotelInfo> {
        return when (sortingType) {
            SortingType.DISTANCE -> hotels.sortedBy { it.distance }
            SortingType.SUITES_AVAILABILITY_COUNT -> hotels.sortedBy { it.suitesAvailability.size }
        }
    }
}
