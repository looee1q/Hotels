package com.example.hotels.data.api

object HttpRoutes {
    const val BASE_HOTELS_URL = "https://raw.githubusercontent.com/iMofas/ios-android-test/master/"
    private const val BASE_HOTEL_IMAGE_URL = "https://github.com/iMofas/ios-android-test/raw/master/"
    const val HOTELS_PATH_SUFFIX = ".json"
    const val HOTELS_LIST_PATH = "0777$HOTELS_PATH_SUFFIX"

    /**
     * Forming full url for hotel image
     *
     * @param imageId An imageId from api
     * @return full url for hotel image
     */
    fun getFullHotelImageUrl(imageId: String?): String? {
        return imageId?.takeIf { it.isNotEmpty() }?.let { BASE_HOTEL_IMAGE_URL + it }
    }
}
