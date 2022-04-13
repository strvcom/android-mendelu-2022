package com.strv.archdemo.ui.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NamesList(names: List<String>){
    val context = LocalContext.current
    LazyColumn(){
        items(items = names){ name ->
            NameItem(modifier = Modifier.clickable {
                Toast.makeText(context, "$name clicked", Toast.LENGTH_SHORT).show()
            }, name = name)
        }
    }
}

@Composable
fun NameItem(modifier: Modifier, name: String){
    Row(
        modifier = modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(horizontal = 4.dp),
            painter = rememberVectorPainter(image = Icons.Default.Person),
            contentDescription = "$name's face"
        )
        Text(text = name)
    }
}

