package com.durov.solutions.manager.screen.error

import com.durov.solutions.manager.screen.error.model.ExceptionState
import kotlinx.coroutines.flow.StateFlow

interface ExceptionHandler {
    val exceptionState: StateFlow<ExceptionState>
    fun dissmiss()
}