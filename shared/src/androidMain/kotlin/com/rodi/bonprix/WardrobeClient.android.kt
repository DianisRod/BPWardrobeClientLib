package com.rodi.bonprix
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.engine.cio.*
import io.ktor.http.content.NullBody

actual fun provideHttpClient(): io.ktor.client.HttpClient {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
    return client
}

actual suspend fun getWardrobeItems(): List<WardrobeItem> {
    try {
        val items : List<WardrobeItem> = WardrobeClient().getItems()
        return items
    }catch (e: Throwable ) {
        e.printStackTrace()
        return emptyList()
    }
}