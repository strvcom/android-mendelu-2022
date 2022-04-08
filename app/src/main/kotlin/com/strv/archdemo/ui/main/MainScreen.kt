package com.strv.archdemo.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.strv.archdemo.ui.theme.ArchDemoTheme

@Composable
fun MainScreen(
    goToCounterClick: () -> Unit,
    viewModel: MainViewModel = viewModel(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "MainScreen") }
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "MainScreen",
                style = MaterialTheme.typography.h3
            )
            Button(
                onClick = goToCounterClick,
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text(
                    text = "Go to Counter".uppercase(),
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    ArchDemoTheme {
        Surface {
            MainScreen {}
        }
    }
}
