package com.example.hotels.data.api

import com.example.hotels.data.dto.HotelDetailsDto
import com.example.hotels.data.dto.HotelInfoDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class HotelsApiImpl(
    private val httpClient: HttpClient
) : HotelsApi {

    override suspend fun getHotelsList(): List<HotelInfoDto> {
        return httpClient
            .get(HttpRoutes.HOTELS_LIST_PATH)
            .body<List<HotelInfoDto>>()
    }

    override suspend fun getHotelInfo(hotelId: Int): HotelDetailsDto {
        return httpClient
            .get(hotelId.toString() + HttpRoutes.HOTELS_PATH_SUFFIX)
            .body<HotelDetailsDto>()
    }
}
