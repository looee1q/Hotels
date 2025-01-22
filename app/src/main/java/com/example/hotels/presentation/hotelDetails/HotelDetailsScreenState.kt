package com.example.hotels.presentation.hotelDetails

import com.example.hotels.domain.models.HotelDetails

sealed interface HotelDetailsScreenState {

    data object Initial : HotelDetailsScreenState

    data object Loading : HotelDetailsScreenState

    data class Loaded(val hotelDetails: HotelDetails) : HotelDetailsScreenState

    data object NoConnectionError : HotelDetailsScreenState

    data object UnknownError : HotelDetailsScreenState
}
