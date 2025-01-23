package com.example.hotels.presentation.utils

import android.content.Context
import com.example.hotels.R
import com.example.hotels.domain.models.FilterType

fun FilterType.mapToString(context: Context) = when (this) {
    FilterType.DISTANCE -> context.getString(R.string.by_distance)
    FilterType.SUITES_AVAILABILITY_COUNT -> context.getString(R.string.by_suites_available)
}
