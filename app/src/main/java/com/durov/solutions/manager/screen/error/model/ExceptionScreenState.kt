package com.durov.solutions.manager.screen.error.model

sealed interface ExceptionScreenState {
    object None : ExceptionScreenState
    class Error(val cause: Throwable) : ExceptionScreenState

}