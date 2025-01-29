package com.example.hotels.presentation.utils

class ImageTooSmallException(
    width: Int,
    height: Int,
    requiredWidth: Int,
    requiredHeight: Int
) : Exception("The size of the image is too small: $width x $height. Minimum size: $requiredWidth x $requiredHeight")
