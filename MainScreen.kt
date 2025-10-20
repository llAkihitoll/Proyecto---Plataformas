package com.example.proyecto.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyecto.R

@Composable
fun MainScreen(
    onRestClick: () -> Unit = {}
) {
    var showFoodMenu by remember { mutableStateOf(false) }
    var showMoodMenu by remember { mutableStateOf(false) }
    var showWitMenu by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu_background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                MenuButton(
                    imageRes = R.drawable.wit,
                    contentDescription = "Wit",
                    onClick = { showWitMenu = true }
                )
                MenuButton(
                    imageRes = R.drawable.mood,
                    contentDescription = "Mood",
                    onClick = { showMoodMenu = true }
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                MenuButton(
                    imageRes = R.drawable.food,
                    contentDescription = "Food",
                    onClick = { showFoodMenu = true }
                )
                MenuButton(
                    imageRes = R.drawable.rest,
                    contentDescription = "Rest",
                    onClick = onRestClick
                )
            }
        }

        if (showFoodMenu) {
            RatingDialog(
                title = "Rate your food",
                question1 = "How healthy?",
                question2 = "How enjoyable?",
                onDismiss = { showFoodMenu = false },
                onAccept = { _, _ -> showFoodMenu = false }
            )
        }

        if (showMoodMenu) {
            RatingDialog(
                title = "Rate your mood",
                question1 = "How enjoyable?",
                question2 = null, // Solo una pregunta
                onDismiss = { showMoodMenu = false },
                onAccept = { _, _ -> showMoodMenu = false }
            )
        }

        if (showWitMenu) {
            RatingDialog(
                title = "Rate your wit",
                question1 = "How important?",
                question2 = "How enjoyable?",
                onDismiss = { showWitMenu = false },
                onAccept = { _, _ -> showWitMenu = false }
            )
        }
    }
}

@Composable
fun MenuButton(
    imageRes: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = contentDescription,
            modifier = Modifier.size(72.dp)
        )
    }
}

@Composable
fun RatingDialog(
    title: String,
    question1: String,
    question2: String?, // puede ser null
    onDismiss: () -> Unit,
    onAccept: (String, String?) -> Unit
) {
    val ratingOptions = (1..10).map { it.toString() }

    var expanded1 by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    var selected1 by remember { mutableStateOf(ratingOptions.first()) }
    var selected2 by remember { mutableStateOf(ratingOptions.first()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(12.dp))

            // Pregunta 1
            Text(question1, style = MaterialTheme.typography.titleMedium)
            Box {
                Text(
                    selected1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded1 = true }
                        .background(Color.LightGray, RoundedCornerShape(4.dp))
                        .padding(12.dp)
                )
                DropdownMenu(
                    expanded = expanded1,
                    onDismissRequest = { expanded1 = false }
                ) {
                    ratingOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selected1 = option
                                expanded1 = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Pregunta 2 (si aplica)
            question2?.let {
                Text(it, style = MaterialTheme.typography.titleMedium)
                Box {
                    Text(
                        selected2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded2 = true }
                            .background(Color.LightGray, RoundedCornerShape(4.dp))
                            .padding(12.dp)
                    )
                    DropdownMenu(
                        expanded = expanded2,
                        onDismissRequest = { expanded2 = false }
                    ) {
                        ratingOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    selected2 = option
                                    expanded2 = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            Button(
                onClick = { onAccept(selected1, if (question2 != null) selected2 else null) }
            ) {
                Text("Accept")
            }
        }
    }
}
