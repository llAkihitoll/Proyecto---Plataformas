package com.example.proyecto

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

enum class MenuType {
    NONE, FOOD, MOOD, WIT, REST
}

class AppViewModel : ViewModel() {
    var currentMenu by mutableStateOf(MenuType.NONE)
        private set

    fun openMenu(type: MenuType) {
        currentMenu = type
    }

    fun closeMenu() {
        currentMenu = MenuType.NONE
    }
}
