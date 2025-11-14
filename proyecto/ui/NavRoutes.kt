package com.example.proyecto.navigation

sealed class Screen(val route: String) {
    object Login : AppScreen("login_screen")
    object Main  : AppScreen("main_screen")
}
