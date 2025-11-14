package com.example.proyecto.navigation

sealed class AppScreen(val route: String) {
    object Login  : AppScreen("login")
    object SignUp : AppScreen("signup")
    object Main   : AppScreen("main")
    object Profile : AppScreen("profile")

}
