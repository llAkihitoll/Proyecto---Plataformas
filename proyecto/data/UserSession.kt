package com.example.proyecto.data

object UserSession {
    private val registeredUsers = mutableSetOf<String>()
    var currentUser: String? = null
        private set

    fun register(username: String): Boolean {
        return if (username.isNotBlank() && !registeredUsers.contains(username)) {
            registeredUsers.add(username)
            true
        } else {
            false
        }
    }

    fun login(username: String): Boolean {
        return if (registeredUsers.contains(username)) {
            currentUser = username
            true
        } else {
            false
        }
    }

    fun logout() {
        currentUser = null
    }
}
