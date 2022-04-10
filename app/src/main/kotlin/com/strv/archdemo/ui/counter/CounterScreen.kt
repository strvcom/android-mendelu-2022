package com.strv.archdemo.ui.counter

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.strv.archdemo.R
import com.strv.archdemo.ui.components.ArchDemoIconButton
import com.strv.archdemo.ui.theme.ArchDemoTheme

@Composable
fun CounterScreen(
    onBack: () -> Unit,
    viewModel: CounterViewModel = viewModel(),
) {
    val state by viewModel.viewState.collectAsState()

    CounterContent(
        viewState = state,
        onIncreaseCounter = viewModel::onPlusClick,
        onDecreaseCounter = viewModel::onMinusClick,
        onBlockingCall = viewModel::onBlockingCallClick,
        onAsyncCall = viewModel::onAsyncCallClick,
        onBack = onBack,
    )
}

@Composable
fun CounterContent(
    viewState: CounterViewState,
    onIncreaseCounter: () -> Unit,
    onDecreaseCounter: () -> Unit,
    onBlockingCall: () -> Unit,
    onAsyncCall: () -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CounterTopBar(onBack)
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {

            Header(state = viewState)

            Counter(
                state = viewState,
                onDecreaseCounter = onDecreaseCounter,
                onIncreaseCounter = onIncreaseCounter
            )

            Network(
                state = viewState,
                onBlockingCall = onBlockingCall,
                onAsyncCall = onAsyncCall,
            )
        }
    }
}

@Composable
private fun CounterTopBar(onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "CounterScreen")
        },
        navigationIcon = {
            IconButton(
                onClick = onBack,
                modifier = Modifier.clip(CircleShape)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
private fun Header(state: CounterViewState) {
    Text(
        text = "CounterScreen",
        style = MaterialTheme.typography.h3,
        modifier = Modifier.padding(top = 24.dp)
    )
    Text(
        text = state.title,
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(top = 16.dp)
    )
}

@Composable
private fun Counter(
    state: CounterViewState,
    onDecreaseCounter: () -> Unit,
    onIncreaseCounter: () -> Unit
) {
    Row {
        Text(
            text = "Click count: ",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 48.dp)
        )

        Text(
            text = "${state.clickCount}",
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 48.dp)
        )
    }
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {

        ArchDemoIconButton(
            onClick = onDecreaseCounter,
            modifier = Modifier
                .padding(top = 48.dp)
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colors.primary)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_minus_1),
                contentDescription = "Decrease counter",
                tint = contentColorFor(backgroundColor = MaterialTheme.colors.primary),
                modifier = Modifier.padding(20.dp)
            )
        }

        ArchDemoIconButton(
            onClick = onIncreaseCounter,
            modifier = Modifier
                .padding(top = 48.dp)
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colors.primary)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus_1),
                contentDescription = "Increase counter",
                tint = contentColorFor(backgroundColor = MaterialTheme.colors.primary),
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

@Composable
private fun Network(
    state: CounterViewState,
    onBlockingCall: () -> Unit,
    onAsyncCall: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 60.dp),
    ) {
        CircularProgressIndicator()
        Column(
            modifier = Modifier.padding(start = 24.dp)
        ) {
            Button(onClick = onBlockingCall) {
                Text(text = "Blocking Call".uppercase())
            }
            Button(onClick = onAsyncCall) {
                Text(text = "Async Call".uppercase())
            }
        }
    }
    Text(
        text = state.callStatus,
        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(top = 4.dp)
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun CounterScreenPreview() {
    val state = CounterViewState(
        title = "Preview",
        clickCount = 0,
        callStatus = "Ready"
    )
    ArchDemoTheme {
        Surface {
            CounterContent(
                viewState = state,
                onIncreaseCounter = {},
                onDecreaseCounter = {},
                onBlockingCall = {},
                onAsyncCall = {},
                onBack = {},
            )
        }
    }
}
