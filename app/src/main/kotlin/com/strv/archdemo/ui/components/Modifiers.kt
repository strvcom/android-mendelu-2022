package com.strv.archdemo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.strv.archdemo.R

@Preview
@Composable
private fun ChainningPreview() {
    Icon(
        painter = rememberVectorPainter(image = Icons.Default.Home),
        modifier = Modifier
            .size(width = 48.dp, height = 48.dp)
            .clip(CircleShape)
            .background(Color.Red),
        tint = White,
        contentDescription = "You can chain modifiers"
    )
}

@Preview
@Composable
private fun OrderingPreviw() {
    Icon(
        painter = rememberVectorPainter(image = Icons.Default.Home),
        modifier = Modifier
            .border(width = 2.dp, Gray)
            .padding(4.dp)
            .background(Black)
            .padding(8.dp)
            .clip(CircleShape)
            .background(Red)
            .padding(8.dp),
        tint = White,
        contentDescription = "Order matters"
    )
}

@Preview
@Composable
private fun OrderingPreviw2() {
    Box(
        modifier = Modifier
            .background(
                color = Gray
            )
    ) {
        Image(
            modifier = Modifier
                .wrapContentSize()
                .background(color = Black)
                .border(width = 2.dp, color = Red)
                .padding(8.dp),

            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = ""
        )
    }
}

@Preview
@Composable
private fun ScopePreview() {
    Row() {
        Box(modifier = Modifier
            .background(color = Red)
            .align(alignment = Alignment.Top))
        Box(
            modifier = Modifier
                .background(color = Black)
                .align(alignment = Alignment.CenterVertically)
        )
        Box(modifier = Modifier
            .background(color = Gray)
            .align(alignment = Alignment.Bottom))
    }


    Column{
        Box(modifier = Modifier
            .background(color = Red)
            .align(alignment = Alignment.Start))
        Box(
            modifier = Modifier
                .background(color = Black)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Box(modifier = Modifier
            .background(color = Gray)
            .align(alignment = Alignment.End))
    }
}


@Preview
@Composable
private fun KahootTest1(){
    Box(
        contentAlignment = Alignment.TopEnd
    ) {
        Box(modifier = Modifier
            .padding(bottom = 50.dp)
            .size(width = 50.dp, height = 300.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(color = Magenta)
            .align(Alignment.CenterStart)
        )

        Row() {
            Box(modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(color = Red))
            Box(modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(color = Green))
        }
    }
}




