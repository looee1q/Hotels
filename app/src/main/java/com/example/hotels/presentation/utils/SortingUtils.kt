package com.example.hotels.presentation.utils

import android.content.Context
import com.example.hotels.R
import com.example.hotels.domain.models.SortingType

fun SortingType.mapToString(context: Context) = when (this) {
    SortingType.DISTANCE -> context.getString(R.string.by_distance)
    SortingType.SUITES_AVAILABILITY_COUNT -> context.getString(R.string.by_suites_available)
}
