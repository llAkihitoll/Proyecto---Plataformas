package com.example.proyecto.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.proyecto.R

@Composable
fun ProfileScreen(
    username: String,
    isDarkTheme: Boolean,
    languageCode: String,
    onToggleTheme: () -> Unit,
    onChangeLanguage: (String) -> Unit,
    onLogout: () -> Unit,
    onBackToMain: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as? Activity

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = stringResource(R.string.profile_welcome, username),
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.profile_welcome, username),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onToggleTheme,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if (isDarkTheme)
                        stringResource(R.string.switch_to_light)
                    else
                        stringResource(R.string.switch_to_dark)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val newLang = if (languageCode == "es") "en" else "es"
                    onChangeLanguage(newLang)
                    // Activity será recreada desde MainActivity al cambiar languageCode
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.change_language))
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onBackToMain,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.back_to_main))
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.logout))
            }
        }
    }
}
