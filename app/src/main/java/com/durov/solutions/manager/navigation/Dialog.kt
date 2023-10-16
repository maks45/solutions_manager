package com.durov.solutions.manager.navigation

sealed interface Dialog {
    object Loading : Dialog

    class Error(val cause: Throwable) : Dialog

    object None : Dialog

}