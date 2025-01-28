package com.example.hotels.presentation.utils

import android.content.Context
import android.util.TypedValue

fun convertPxToDp(context: Context, pixels: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        pixels.toFloat(),
        context.resources.displayMetrics
    ).toInt()
}
