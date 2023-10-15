package com.durov.solutions.manager.navigation

sealed interface Screen {
    object Home : Screen

    class Subject(val id: Long? = null) : Screen

    object Factor : Screen

    object Solution : Screen

}