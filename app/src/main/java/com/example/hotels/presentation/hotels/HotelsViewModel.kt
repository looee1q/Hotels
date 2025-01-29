package com.example.hotels.presentation.hotels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotels.domain.models.ApiError
import com.example.hotels.domain.models.ApiResult
import com.example.hotels.domain.models.SortingType
import com.example.hotels.domain.usecases.SortHotelsUseCase
import com.example.hotels.domain.usecases.GetHotelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelsViewModel @Inject constructor(
    private val getHotelsUseCase: GetHotelsUseCase,
    private val sortHotelsUseCase: SortHotelsUseCase
) : ViewModel() {

    private val _screenState: MutableStateFlow<HotelsScreenState> = MutableStateFlow(
        HotelsScreenState.Initial
    )
    val screenState = _screenState.asStateFlow()

    init {
        getHotels()
    }

    fun sortHotels(sortingType: SortingType) {
        val currentScreenState = _screenState.value
        if (currentScreenState is HotelsScreenState.Loaded) {
            val sortedHotels = sortHotelsUseCase(
                hotels = currentScreenState.hotels,
                sortingType = sortingType
            )
            _screenState.update { HotelsScreenState.Loaded(sortedHotels) }
        }
    }

    fun retryGetHotels() {
        if (screenState.value !is HotelsScreenState.Loading) {
            getHotels()
        }
    }

    private fun getHotels() {
        _screenState.update { HotelsScreenState.Loading }
        viewModelScope.launch {
            when (val hotelsResult = getHotelsUseCase()) {
                is ApiResult.Success -> {
                    if (hotelsResult.data.isEmpty()) {
                        _screenState.update { HotelsScreenState.EmptyList }
                    } else {
                        _screenState.update {
                            HotelsScreenState.Loaded(hotelsResult.data)
                        }
                    }
                }

                is ApiResult.Error -> {
                    when (hotelsResult.error) {
                        ApiError.NoConnection -> {
                            _screenState.update { HotelsScreenState.NoConnectionError }
                        }

                        ApiError.OtherError -> {
                            _screenState.update { HotelsScreenState.UnknownError }
                        }
                    }
                }
            }
        }
    }
}
