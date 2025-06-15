package com.rodi.bonprix

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun provideHttpClient(): io.ktor.client.HttpClient {
    val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
    return client
}

actual suspend fun getWardrobeItems(): List<WardrobeItem> {
    return WardrobeClient().commonGetWardrobeItems()
}