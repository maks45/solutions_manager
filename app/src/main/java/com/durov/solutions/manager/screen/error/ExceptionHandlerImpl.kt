package com.durov.solutions.manager.screen.error

import com.durov.solutions.manager.screen.error.model.ExceptionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExceptionHandlerImpl() : ExceptionHandler {
    private val _exceptionState =
        MutableStateFlow<ExceptionState>(ExceptionState.None)
    override val exceptionState: StateFlow<ExceptionState>
        get() = _exceptionState.asStateFlow()

    override fun dissmiss() {
        _exceptionState.value = ExceptionState.None
    }

}
