package com.example.hotels.presentation.utils

import android.graphics.Bitmap
import coil3.size.Size
import coil3.transform.Transformation

object ImageUtils {

    const val MIN_HOTELS_IMAGE_WIDTH = 748

    const val MIN_HOTELS_IMAGE_HEIGHT = 418

    const val TOO_SMALL_IMAGE_TAG = "TOO_SMALL_IMAGE"

    fun validateImageSize(width: Int, height: Int, minWidth: Int, minHeight: Int) {
        if (width < minWidth || height < minHeight) {
            throw ImageTooSmallException(width, height, minWidth, minHeight)
        }
    }

    fun clipTransformation(
        pixelsToClip: Int = 1
    ): Transformation = object : Transformation() {

        override val cacheKey: String = "size_1dp_clipping"

        override suspend fun transform(input: Bitmap, size: Size): Bitmap {
            val width = input.width
            val height = input.height
            val clippedBitmap = Bitmap.createBitmap(
                input,
                pixelsToClip,
                pixelsToClip,
                width - 2 * pixelsToClip,
                height - 2 * pixelsToClip
            )
            input.recycle()
            return clippedBitmap
        }
    }
}
