package com.rodi.bonprix

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect fun provideHttpClient(): HttpClient

val URL : String = "https://assets.www.bonprix.com"
val PORT : String = "443" // HTTPS
val ENDPOINT : String = "/codingchallenge/wardrobe.json"

class WardrobeClient {

    suspend fun getItems(color: String?=null, category: String?=null): List<WardrobeItem> {
        val client = provideHttpClient()
        val fullPath = "$URL:$PORT$ENDPOINT"
        var wardrobeItems : List<WardrobeItem> = client.get(fullPath).body()
        if (color != null) {
            wardrobeItems= wardrobeItems.filter { it.color == color }
        }
        if (category != null) {
            wardrobeItems = wardrobeItems.filter { it.category == category }
        }
        return wardrobeItems
    }

    suspend fun getItemsByColor(color: String): List<WardrobeItem> {
        return getItems(color, null)
    }

    suspend fun getItemsByCategory(category: String): List<WardrobeItem> {
        return getItems(null, category)
    }

    suspend fun getItemById(id: Long): WardrobeItem? {
        val wardrobeItems = getItems(null, null)
        val itemById = wardrobeItems.filter { it.id == id }
        return itemById.firstOrNull()
    }

}