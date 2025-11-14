package com.example.proyecto.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object StatsState {
    var sleep by mutableStateOf(0)
    var health by mutableStateOf(0)
    var mood by mutableStateOf(0)
    var intelligence by mutableStateOf(0)

    var sleepState by mutableStateOf(0)        // -1, 0, 1
    var healthState by mutableStateOf(0)
    var moodState by mutableStateOf(0)
    var intState by mutableStateOf(0)

    // Lógica para actualizar estados basado en valores
    fun updateStates() {
        // HEALTH
        if (health < -15) health = -15
        if (health > 15) health = 15
        healthState = when {
            health > 5 -> 1
            health >= -5 -> 0
            else -> -1
        }

        // MOOD
        if (mood < -15) mood = -15
        if (mood > 15) mood = 15
        moodState = when {
            mood > 5 -> 1
            mood >= -5 -> 0
            else -> -1
        }

        // INTELLIGENCE
        if (intelligence < -15) intelligence = -15
        if (intelligence > 15) intelligence = 15
        intState = when {
            intelligence > 5 -> 1
            intelligence >= -5 -> 0
            else -> -1
        }

        // SLEEP
        if (sleep < -15) sleep = -15
        if (sleep > 15) sleep = 15
        sleepState = when {
            sleep > 5 -> 1
            sleep >= -5 -> 0
            else -> -1
        }
    }
}
