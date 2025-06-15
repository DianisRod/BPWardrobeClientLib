package com.rodi.bonprix

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class WardrobeClientTest {
    object Constants{
        const val ID: String = "17"
        const val CATEGORY: String = "Jacket"
        const val COLOR: String = "White"
        const val COMPATIBLE_ITEM: String = "Dress"

    }

    @Test
    fun testGetItems() = runTest {
        val wardrobeClient = WardrobeClient()
        val items: List<WardrobeItem> = wardrobeClient.getItems()
        assertTrue { items.isNotEmpty() }
    }

    @Test
    fun testGetItemsByColor() = runTest {
        val wardrobeClient = WardrobeClient()
        val whiteItems: List<WardrobeItem> = wardrobeClient.getItemsByColor(Constants.COLOR)

        assertTrue { whiteItems.isNotEmpty() }

        val nonWhiteItem = whiteItems.find { item -> item.color != Constants.COLOR }
        assertEquals(null, nonWhiteItem, "Found an item that is not $Constants.COLOR: $nonWhiteItem")
    }

    @Test
    fun testGetItemsByCategory() = runTest {
        val wardrobeClient = WardrobeClient()
        val whiteItems: List<WardrobeItem> = wardrobeClient.getItemsByCategory(Constants.CATEGORY)

        assertTrue { whiteItems.isNotEmpty() }

        val nonJacketItem = whiteItems.find { item -> item.category != Constants.CATEGORY }
        assertEquals(null, nonJacketItem, "Found an item that is not $Constants.CATEGORY: $nonJacketItem")
    }

    @Test
    fun testGetItemById() = runTest {
        val wardrobeClient = WardrobeClient()
        val item = wardrobeClient.getItemById(Constants.ID)

        assertTrue { item!=null }
        assertEquals(17L, item?.id)
        assertEquals("Wrap Dress", item?.name)
        assertEquals("Dress", item?.category)
        assertEquals("Emerald", item?.color)
        assertEquals("Jersey", item?.material)
        assertContentEquals(listOf("Shoes", "Accessories"), item?.compatibleWith)
    }

    @Test
    fun testGetItemByCompatibleItem() = runTest {
        val wardrobeClient = WardrobeClient()
        val items: List<WardrobeItem> = wardrobeClient.getItemByCompatibleItem(Constants.COMPATIBLE_ITEM)

        assertTrue { items.isNotEmpty() }
        val itemWithoutSpecifiedCompItem : WardrobeItem? = items.find { !it.compatibleWith.contains(Constants.COMPATIBLE_ITEM) }
        assertEquals(null, itemWithoutSpecifiedCompItem)
    }

    @Test
    fun testCommonWardrobeItemsIsNotNull() = runTest {
        val wardrobeClient = WardrobeClient()
        val getItems = wardrobeClient.commonGetWardrobeItems(Constants.COLOR, Constants.COMPATIBLE_ITEM)
        assertTrue { getItems.isNotEmpty() }
    }

    @Test
    fun testCommonWardrobeItemsIsNull() = runTest {
        val wardrobeClient = WardrobeClient()
        val getItems = wardrobeClient.commonGetWardrobeItems(null, "nonExistingCategory")
        assertTrue { getItems.isEmpty() }
    }
}