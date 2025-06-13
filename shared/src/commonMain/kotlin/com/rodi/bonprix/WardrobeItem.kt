package com.rodi.bonprix

import kotlinx.serialization.Serializable

@Serializable
data class WardrobeItem(val id: Long,
                        val name: String,
                        val category: String,
                        val color: String,
                        val material: String,
                        val compatibleWith: List<String>
)

//fun WardrobeItem.toString(): String {
//    return "WardrobeItem(id=$id, name='$name', category='$category', color='$color', material='$material', compatibleWith=$compatibleWith)"
//}
