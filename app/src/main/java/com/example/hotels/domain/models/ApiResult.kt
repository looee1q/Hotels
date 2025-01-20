package com.example.hotels.domain.models

sealed interface ApiResult<out D, out E : ApiError> {

    data class Success<out D>(val data: D) : ApiResult<D, Nothing>

    data class Error<out E : ApiError>(val error: E) : ApiResult<Nothing, E>
}

sealed interface ApiError {

    data object NoConnection : ApiError

    data object OtherError : ApiError
}