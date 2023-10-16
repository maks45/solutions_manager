package com.durov.solutions.manager.screen.error.model

sealed interface ExceptionState {
    object None : ExceptionState
    class Error(val cause: Throwable) : ExceptionState

}