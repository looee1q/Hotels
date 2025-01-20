package com.example.hotels.data.repositoryimpl

import android.net.ConnectivityManager
import com.example.hotels.data.api.HotelsApi
import com.example.hotels.data.model.dto.HotelDetailsDto
import com.example.hotels.data.model.dto.HotelInfoDto
import com.example.hotels.data.model.mappers.Mapper
import com.example.hotels.data.utils.isDeviceConnectedToNetwork
import com.example.hotels.domain.models.ApiError
import com.example.hotels.domain.models.ApiResult
import com.example.hotels.domain.models.HotelDetails
import com.example.hotels.domain.models.HotelInfo
import com.example.hotels.domain.repository.HotelsRepository

class HotelsRepositoryImpl(
    private val hotelsApi: HotelsApi,
    private val connectivityManager: ConnectivityManager,
    private val hotelInfoMapper: Mapper<HotelInfoDto, HotelInfo>,
    private val hotelDetailsMapper: Mapper<HotelDetailsDto, HotelDetails>,
) : HotelsRepository {

    override suspend fun getHotels(): ApiResult<List<HotelInfo>, ApiError> {
        if (!connectivityManager.isDeviceConnectedToNetwork()) {
            return ApiResult.Error(ApiError.NoConnection)
        }
        return try {
            val hotels = hotelsApi.getHotelsList().map { hotelInfoMapper.map(it) }
            ApiResult.Success(hotels)
        } catch (e: Exception) {
            ApiResult.Error(ApiError.OtherError)
        }
    }

    override suspend fun getHotelDetails(hotelId: Int): ApiResult<HotelDetails, ApiError> {
        if (!connectivityManager.isDeviceConnectedToNetwork()) {
            return ApiResult.Error(ApiError.NoConnection)
        }
        return try {
            val hotelDetails = hotelsApi.getHotelInfo(hotelId).let(hotelDetailsMapper::map)
            ApiResult.Success(hotelDetails)
        } catch (e: Exception) {
            ApiResult.Error(ApiError.OtherError)
        }
    }
}