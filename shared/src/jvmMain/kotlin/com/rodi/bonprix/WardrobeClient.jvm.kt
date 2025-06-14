package com.rodi.bonprix

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.printStackTrace

actual fun provideHttpClient(): io.ktor.client.HttpClient {
    val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
    return client
}

actual suspend fun getWardrobeItems(): List<WardrobeItem> {
    try {
        //throw NullPointerException("So kann ein Fehler in dem catch-Block in die Console geschrieben werden")
        //val wardrobeItem = WardrobeItem(4711, "Frilufts", "Jacket", "black", "fleece", listOf("Hiking boots"))
        //return listOf(wardrobeItem)
        val items : List<WardrobeItem> = WardrobeClient().getItems()
        return items
    }catch (e: Throwable ) {
        e.printStackTrace()
        return emptyList()
    }
}