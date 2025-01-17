package com.example.hotels.data.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import kotlinx.serialization.json.Json

object HttpKtorClient {

    fun getClient() = HttpClient(OkHttp.create()) {

        install(ContentNegotiation) {
            register(
                contentType = ContentType.Text.Plain,
                converter = KotlinxSerializationConverter(
                    Json { ignoreUnknownKeys = true }
                )
            )
        }

        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }

        install(DefaultRequest) {
            url(HttpRoutes.BASE_HOTELS_URL)
        }
    }
}
