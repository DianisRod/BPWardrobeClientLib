package sample.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row (){
                Button(onClick = {
                    wardrobeItemsText = "Loading..."
                    coroutineScope.launch {
                        wardrobeItemsText = getUiWardrobeItems()
                    }
                }) {
                    Text("Get wardrobe items!")
                }
                Button(onClick = {
                    wardrobeItemsText = ""

                }) {
                    Text("Clear")
                }
            }

            TextField(
                value = wardrobeItemsText,
                onValueChange = { wardrobeItemsText = it },
                label = { Text("") },
                modifier = Modifier.width(1200.dp)
            )
        }
    }
}

private suspend fun getUiWardrobeItems():  String  {
    val wardrobeItems : List<WardrobeItem> = getWardrobeItems()

    // alle items:
    //val itemsString : String = wardrobeItems.joinToString("\n") { it.name }

    // alle items mit Index:
    //val idxRange = (0 .. wardrobeItems.size-1)
    //val itemsString = idxRange.joinToString("\n") { it.toString() +" "+ wardrobeItems[it].name }

    // alle items mit Index und kompatiblen Teilen:
    val idxRange = (0 .. wardrobeItems.size-1)
    val itemsString = idxRange.joinToString("\n") { it.toString() +": "+ wardrobeItems[it].name+",\n\tcompatible with "+wardrobeItems[it].compatibleWith }

    // oder alternativ eine Tabelle mit allen Informationen

    println(itemsString)
    return(itemsString)
}