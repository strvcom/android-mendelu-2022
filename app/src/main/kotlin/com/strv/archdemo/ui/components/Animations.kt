package com.strv.archdemo.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun AnimatedVisibilityPreview() {
    var visible by remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(visible = visible) {
                Box(
                    Modifier
                        .size(200.dp)
                        .background(MaterialTheme.colors.primary)
                )
            }
        }

        Button(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            onClick = { visible = visible.not() }) {
            Text(text = if (visible) "Hide" else "Show")
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun AnimatedContentPreview() {
    var visible by remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(visible = visible) {
                Box(
                    Modifier
                        .size(200.dp)
                        .background(MaterialTheme.colors.primary)
                )
            }
        }

        Button(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            onClick = { visible = visible.not() }) {
            AnimatedContent(targetState = visible) {
                if (it) Text(text = "Hide") else Text(text = "Show")
            }
        }
    }
}

enum class CrossfadeStates {
    FIRST,
    SECOND,
    THIRD
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun CrossFadePreview() {
    var state by remember { mutableStateOf<CrossfadeStates>(CrossfadeStates.FIRST) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Crossfade(targetState = state) {
                when (it) {
                    CrossfadeStates.FIRST -> {
                        Box(
                            Modifier
                                .size(200.dp)
                                .background(Color.Green)
                        )
                    }
                    CrossfadeStates.SECOND -> {
                        Box(
                            Modifier
                                .size(200.dp)
                                .background(Color.Blue)
                                .clip(CircleShape)
                        )
                    }
                    CrossfadeStates.THIRD -> {
                        Box(
                            Modifier
                                .size(200.dp)
                                .background(Color.Red)
                                .clip(CircleShape)
                        )
                    }
                }
            }
        }

        Button(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            onClick = {
                state = CrossfadeStates.values().filter { it != state }.random()
            }) {
            Text(text = "Show random state")
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun AnimateDpAsStatePreview() {
    var isBig by remember { mutableStateOf(true) }

    val boxDp by animateDpAsState(targetValue = if (isBig) 200.dp else 50.dp)
    val boxColor by animateColorAsState(targetValue = if (isBig) Color.Blue else Color.Red)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                Modifier
                    .size(boxDp)
                    .background(boxColor)
            )
        }

        Button(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            onClick = { isBig = isBig.not() }) {
            AnimatedContent(targetState = isBig) {
                if (it) Text(text = "Make it small") else Text(text = "Make it big")
            }
        }
    }
}

enum class SwitchState {
    MONTHLY,
    YEARLY
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun SwitcherPreview() {
    var switchState by remember { mutableStateOf(SwitchState.MONTHLY) }
    val animationState by animateFloatAsState(
        targetValue = when (switchState) {
            SwitchState.MONTHLY -> 0f
            SwitchState.YEARLY -> 1f
        }
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    color = Color(0xFF7A2500).copy(alpha = 0.6f),
                    shape = RoundedCornerShape(100.dp)
                )
                .clip(RoundedCornerShape(100.dp))
        ) {
            Spacer(modifier = Modifier.weight(animationState + Float.MIN_VALUE))
            Box(
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(
                        color = Color(0xFF7A2500),
                        shape = RoundedCornerShape(100.dp)
                    )
                    .clip(RoundedCornerShape(100.dp))
            )
            Spacer(modifier = Modifier.weight(1f - animationState + Float.MIN_VALUE))
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable { switchState = SwitchState.MONTHLY }, text = "MONTHLY",
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable { switchState = SwitchState.YEARLY }, text = "YEARLY",
                textAlign = TextAlign.Center
            )
        }
    }
}

//AnimatedContent(
//targetState = state.clickCount,
//transitionSpec = {
//    // Compare the incoming number with the previous number.
//    if (targetState > initialState) {
//        // If the target number is larger, it slides up and fades in
//        // while the initial (smaller) number slides up and fades out.
//        slideInVertically { height -> height } + fadeIn() with
//                slideOutVertically { height -> -height } + fadeOut()
//    } else {
//        // If the target number is smaller, it slides down and fades in
//        // while the initial number slides down and fades out.
//        slideInVertically { height -> -height } + fadeIn() with
//                slideOutVertically { height -> height } + fadeOut()
//    }.using(
//        // Disable clipping since the faded slide-in/out should
//        // be displayed out of bounds.
//        SizeTransform(clip = false)
//    )
//}
//) { count ->
//    Text(
//        text = "${count}",
//        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
//    )
//}