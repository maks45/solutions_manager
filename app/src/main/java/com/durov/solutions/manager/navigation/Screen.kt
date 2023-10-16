package com.durov.solutions.manager.navigation

sealed interface Screen {
    object Home : Screen

    class Subject(val id: Long) : Screen

    class Factor(val id: Long) : Screen

    class Solution(val id: Long) : Screen

}
