package com.durov.solutions.manager.screen.error

import com.durov.solutions.manager.screen.error.model.ExceptionScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.StateFlow

interface ExceptionHandler {
    val exceptionState: StateFlow<ExceptionScreenState>
    val mainExceptionHandler: CoroutineExceptionHandler
    val ignoreExceptionHandler: CoroutineExceptionHandler
    fun dissmiss()
}