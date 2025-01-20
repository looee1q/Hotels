package com.example.hotels.domain.models

data class HotelDetails(
    val id: Int,
    val name: String,
    val address: String,
    val stars: Float,
    val distance: Float,
    val imageUrl: String?,
    val suitesAvailability: List<Int>,
    val latitude: Double,
    val longitude: Double
)
