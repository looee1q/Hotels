package com.example.hotels.presentation.hotels

import com.example.hotels.domain.models.HotelInfo

sealed interface HotelsScreenState {

    data object Initial : HotelsScreenState

    data object Loading : HotelsScreenState

    data class Loaded(val hotels: List<HotelInfo>) : HotelsScreenState

    data object EmptyList : HotelsScreenState

    data object NoConnectionError : HotelsScreenState

    data object UnknownError : HotelsScreenState
}
