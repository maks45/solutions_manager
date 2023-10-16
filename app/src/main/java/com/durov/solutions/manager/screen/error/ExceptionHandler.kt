package com.durov.solutions.manager.screen.error

import com.durov.solutions.manager.screen.error.model.ExceptionState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.StateFlow

interface ExceptionHandler {
    val exceptionState: StateFlow<ExceptionState>
    val mainExceptionHandler: CoroutineExceptionHandler
    val ignoreExceptionHandler: CoroutineExceptionHandler
    fun dissmiss()
}