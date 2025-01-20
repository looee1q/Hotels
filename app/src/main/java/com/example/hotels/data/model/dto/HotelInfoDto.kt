package com.example.hotels.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HotelInfoDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("address") val address: String,
    @SerialName("stars") val stars: Float,
    @SerialName("distance") val distance: Float,
    @SerialName("suites_availability") val suitesAvailability: String
)
