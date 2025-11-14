package com.example.proyecto.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.proyecto.R
import com.example.proyecto.data.StatsState

@Composable
fun CharacterView(
    modifier: Modifier = Modifier,
    size: Dp = 220.dp
) {
    val faceRes = when (StatsState.healthState) {
        1 -> R.drawable.face_good
        0 -> R.drawable.face_neutral
        else -> R.drawable.face_bad
    }
    val eyesRes = when (StatsState.sleepState) {
        1 -> R.drawable.eyes_good
        0 -> R.drawable.eyes_neutral
        else -> R.drawable.eyes_bad
    }
    val mouthRes = when (StatsState.moodState) {
        1 -> R.drawable.mouth_good
        0 -> R.drawable.mouth_neutral
        else -> R.drawable.mouth_bad
    }

    Box(
        modifier = modifier
            .size(size)
            .wrapContentSize(Alignment.Center)
    ) {
        // Cara
        Image(
            painter = painterResource(id = faceRes),
            contentDescription = "Face",
            modifier = Modifier
                .align(Alignment.Center)
                .size(size)
        )

        // Ojos
        Image(
            painter = painterResource(id = eyesRes),
            contentDescription = "Eyes",
            modifier = Modifier
                .size(size * 0.25f)
                .align(Alignment.Center)
                .offset(y = -(size * 0.2f))
        )

        // Boca
        Image(
            painter = painterResource(id = mouthRes),
            contentDescription = "Mouth",
            modifier = Modifier
                .size(size * 0.15f)
                .align(Alignment.Center)
                .offset(y = size * 0.2f)
        )
    }
}

