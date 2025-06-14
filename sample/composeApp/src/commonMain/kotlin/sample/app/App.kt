package sample.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodi.bonprix.WardrobeItem
import com.rodi.bonprix.getWardrobeItems
import kotlinx.coroutines.launch

@Composable
fun App() {
    val coroutineScope = rememberCoroutineScope()

    MaterialTheme {
        var wardrobeItemsText by remember { mutableStateOf("") }
        var categoryItemsText by remember { mutableStateOf("") }
        var colorItemsText by remember { mutableStateOf("") }

        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        ) {
            Column(
                modifier = Modifier
                    .safeContentPadding()
                    .fillMaxSize()
                    .width(700.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier,//.fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    OutlinedTextField(
                        value = categoryItemsText,
                        onValueChange = { categoryItemsText = it },
                        label = {
                            Text("category")
                        }
                    )
                }

                Row(
                    modifier = Modifier,//.fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    OutlinedTextField(
                        value = colorItemsText,
                        onValueChange = { colorItemsText = it },
                        label = {
                            Text("color")
                        }
                    )
                }

                Row(
                    modifier = Modifier,//.fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(onClick = {
                        wardrobeItemsText = "Loading..."
                        coroutineScope.launch {
                            wardrobeItemsText = getUiWardrobeItems(colorItemsText, categoryItemsText)
                        }
                    }) {
                        Text("Get wardrobe items!")
                    }
                    Button(onClick = {
                        wardrobeItemsText = ""
                        categoryItemsText = ""
                        colorItemsText = ""

                    }) {
                        Text("Clear")
                    }
                }

                TextField(
                    value = wardrobeItemsText,
                    onValueChange = { wardrobeItemsText = it },
                    label = { Text("") },
                    modifier = Modifier
                        .fillMaxHeight()
                )
            }
        }
    }

}

private suspend fun getUiWardrobeItems(color: String?, category: String?): String {
    val wardrobeItems: List<WardrobeItem> = getWardrobeItems(color, category)

    // alle items:
    //val itemsString : String = wardrobeItems.joinToString("\n") { it.name }

    // alle items mit Index:
    //val idxRange = (0 .. wardrobeItems.size-1)
    //val itemsString = idxRange.joinToString("\n") { it.toString() +" "+ wardrobeItems[it].name }

    // alle items mit Index und kompatiblen Teilen:

    val idxRange = (0..wardrobeItems.size - 1)
    val itemsString = idxRange.joinToString("\n") {
        wardrobeItems[it].toString()
    }

    println(itemsString)
    return (itemsString)
}