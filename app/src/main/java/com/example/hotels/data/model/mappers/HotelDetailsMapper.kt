package com.example.hotels.data.model.mappers

import com.example.hotels.data.api.HttpRoutes
import com.example.hotels.data.model.dto.HotelDetailsDto
import com.example.hotels.data.utils.ParsingUtils
import com.example.hotels.domain.models.HotelDetails

class HotelDetailsMapper : Mapper<HotelDetailsDto, HotelDetails> {

    override fun map(input: HotelDetailsDto): HotelDetails {
        return HotelDetails(
            id = input.id,
            name = input.name,
            address = input.address,
            stars = input.stars,
            distance = input.distance,
            imageUrl = HttpRoutes.getFullHotelImageUrl(input.imageId),
            suitesAvailability = ParsingUtils.parseSuitesAvailabilityStringToList(
                input.suitesAvailability
            ),
            latitude = input.latitude,
            longitude = input.longitude
        )
    }
}