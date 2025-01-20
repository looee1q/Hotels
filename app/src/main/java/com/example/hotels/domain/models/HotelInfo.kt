package com.example.hotels.domain.models

data class HotelInfo(
    val id: Int,
    val name: String,
    val address: String,
    val stars: Float,
    val distance: Float,
    val suitesAvailability: List<Int>
)
