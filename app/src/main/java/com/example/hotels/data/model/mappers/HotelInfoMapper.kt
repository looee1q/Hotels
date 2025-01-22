package com.example.hotels.data.model.mappers

import com.example.hotels.data.model.dto.HotelInfoDto
import com.example.hotels.data.utils.ParsingUtils
import com.example.hotels.domain.models.HotelInfo
import javax.inject.Inject

class HotelInfoMapper @Inject constructor() : Mapper<HotelInfoDto, HotelInfo> {

    override fun map(input: HotelInfoDto): HotelInfo {
        return HotelInfo(
            id = input.id,
            name = input.name,
            address = input.address,
            stars = input.stars,
            distance = input.distance,
            suitesAvailability = ParsingUtils.parseSuitesAvailabilityStringToList(
                input.suitesAvailability
            )
        )
    }
}
