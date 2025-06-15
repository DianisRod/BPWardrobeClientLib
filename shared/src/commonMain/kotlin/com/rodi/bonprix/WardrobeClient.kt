package com.rodi.bonprix

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

expect fun provideHttpClient(): HttpClient
expect suspend fun getWardrobeItems(): List<WardrobeItem>

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

    suspend fun getItemById(id: String): WardrobeItem? {
        return getItems().find { it.id.toString() == id }
    }

    suspend fun getItemByCompatibleItem(compatibleItem: String): WardrobeItem? {
        return TODO()
    }

    suspend fun commonGetWardrobeItems(): List<WardrobeItem> {
        try {
            val items : List<WardrobeItem> = WardrobeClient().getItems()
            return items
        }catch (e: Throwable ) {
            e.printStackTrace()
            return emptyList()
        }
    }

}