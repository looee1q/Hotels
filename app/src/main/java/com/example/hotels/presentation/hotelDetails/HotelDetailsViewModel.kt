package com.example.hotels.presentation.hotelDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotels.domain.models.ApiError
import com.example.hotels.domain.models.ApiResult
import com.example.hotels.domain.usecases.GetHotelDetailsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelDetailsViewModel @Inject constructor(
    private val getHotelDetailsByIdUseCase: GetHotelDetailsByIdUseCase
) : ViewModel() {

    private val _screenState: MutableStateFlow<HotelDetailsScreenState> = MutableStateFlow(
        HotelDetailsScreenState.Initial
    )
    val screenState = _screenState.asStateFlow()

    private fun getHotelDetails(hotelId: Int) {
        _screenState.update { HotelDetailsScreenState.Loading }
        viewModelScope.launch {
            when (val hotelDetailsResult = getHotelDetailsByIdUseCase(hotelId)) {
                is ApiResult.Success -> {
                    _screenState.update { HotelDetailsScreenState.Loaded(hotelDetailsResult.data) }
                }

                is ApiResult.Error -> {
                    when (hotelDetailsResult.error) {
                        ApiError.NoConnection -> {
                            _screenState.update { HotelDetailsScreenState.NoConnectionError }
                        }

                        ApiError.OtherError -> {
                            _screenState.update { HotelDetailsScreenState.UnknownError }
                        }
                    }
                }
            }
        }
    }
}
