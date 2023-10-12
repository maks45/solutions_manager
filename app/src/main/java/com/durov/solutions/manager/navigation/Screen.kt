package com.durov.solutions.manager.navigation

sealed interface Screen {
    object Home : Screen

    object Subject : Screen

    object Factor : Screen

    object Solution : Screen

}