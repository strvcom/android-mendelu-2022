package com.strv.archdemo.ui.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.strv.archdemo.R

@Preview
@Composable
private fun ComponentsPreview() {
    Column {
        Text(text = "I'm beatiful text")
        Image(
            painter = painterResource(id = R.drawable.blyat),
            contentDescription = "Blyat"
        )
        Icon(
            painter = rememberVectorPainter(image = Icons.Default.Favorite),
            contentDescription = ""
        )
        Button(onClick = {}) {
            Text(text = "I'm beautiful button")
        }
        IconButton(onClick = {}) {
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.Favorite),
                contentDescription = ""
            )
        }
        FloatingActionButton(onClick = {}) {
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.Favorite),
                contentDescription = ""
            )
        }
        RadioButton(selected = true, onClick = {})
        Switch(checked = false, onCheckedChange = {})
    }
}

@Preview
@Composable
private fun CompoentNestingPreview() {
    val context = LocalContext.current
    Button(
        onClick = {
            Toast.makeText(context, "Red button clicked", Toast.LENGTH_SHORT).show()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
        modifier = Modifier.padding(8.dp)

    ) {
        Button(
            onClick = {
                Toast.makeText(context, "Blue button clicked", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
            modifier = Modifier.padding(8.dp)
        ) {
            Button(
                onClick = {
                    Toast.makeText(context, "Green button clicked", Toast.LENGTH_SHORT).show()

                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                modifier = Modifier.padding(8.dp)
            ) {

            }
        }

    }
}