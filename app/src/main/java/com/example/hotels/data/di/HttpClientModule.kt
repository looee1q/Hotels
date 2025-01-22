package com.example.hotels.data.di

import com.example.hotels.data.api.HttpRoutes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HttpClientModule {

    @Provides
    @Singleton
    fun providesHttpClientEngine(): HttpClientEngine {
        return OkHttp.create()
    }

    @Provides
    @Singleton
    fun providesJson(): Json {
        return Json { ignoreUnknownKeys = true }
    }

    @Provides
    @Singleton
    fun providesHttpClient(
        httpClientEngine: HttpClientEngine,
        json: Json
    ): HttpClient {
        return HttpClient(httpClientEngine) {

            install(ContentNegotiation) {
                register(
                    contentType = ContentType.Text.Plain,
                    converter = KotlinxSerializationConverter(json)
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
}
