package com.example.proyecto

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.os.LocaleListCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto.navigation.AppScreen
import com.example.proyecto.ui.LoginScreen
import com.example.proyecto.ui.SignUpScreen
import com.example.proyecto.ui.MainScreen
import com.example.proyecto.ui.ProfileScreen
import com.example.proyecto.ui.theme.ProyectoTheme
import com.example.proyecto.data.UserSession
import androidx.compose.runtime.saveable.rememberSaveable


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var isDarkTheme by rememberSaveable { mutableStateOf(false) }
            var languageCode by rememberSaveable { mutableStateOf("en") }

            // Aplica el idioma al inicio o cuando cambie, pero sin recrear constantemente
            LaunchedEffect(languageCode) {
                val localeList = LocaleListCompat.forLanguageTags(languageCode)
                AppCompatDelegate.setApplicationLocales(localeList)
                // Aquí puedes recrear solo si realmente cambiaste el idioma y quieres una actualización completa
                // recreate()
            }

            ProyectoTheme(darkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = AppScreen.Login.route
                    ) {
                        composable(AppScreen.Login.route) {
                            LoginScreen(
                                navController = navController,
                                onLoginSuccess = {
                                    navController.navigate(AppScreen.Main.route) {
                                        popUpTo(AppScreen.Login.route) { inclusive = true }
                                    }
                                },
                                onRegisterClick = {
                                    navController.navigate(AppScreen.SignUp.route)
                                }
                            )
                        }
                        composable(AppScreen.SignUp.route) {
                            SignUpScreen(
                                navController = navController,
                                onSignUpSuccess = {
                                    navController.popBackStack()
                                }
                            )
                        }
                        composable(AppScreen.Main.route) {
                            MainScreen(
                                navController = navController,
                                onRestClick = { /* acción Rest */ }
                            )
                        }
                        composable(AppScreen.Profile.route) {
                            ProfileScreen(
                                username = UserSession.currentUser.orEmpty(),
                                isDarkTheme = isDarkTheme,
                                languageCode = languageCode,
                                onToggleTheme = { isDarkTheme = !isDarkTheme },
                                onChangeLanguage = { newCode ->
                                    if (newCode != languageCode) {
                                        languageCode = newCode
                                        // Opcional: recreate() aquí si deseas forzar
                                        this@MainActivity.recreate()
                                    }
                                },
                                onLogout = {
                                    UserSession.logout()
                                    navController.navigate(AppScreen.Login.route) {
                                        popUpTo(AppScreen.Main.route) { inclusive = true }
                                    }
                                },
                                onBackToMain = {
                                    navController.navigate(AppScreen.Main.route) {
                                        popUpTo(AppScreen.Profile.route) { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
