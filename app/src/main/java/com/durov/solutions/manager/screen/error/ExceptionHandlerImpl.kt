package com.durov.solutions.manager.screen.error

import com.durov.solutions.manager.screen.error.model.ExceptionState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class ExceptionHandlerImpl : ExceptionHandler {
    private val _exceptionState =
        MutableStateFlow<ExceptionState>(ExceptionState.None)
    override val exceptionState: StateFlow<ExceptionState>
        get() = _exceptionState.asStateFlow()
    override val mainExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            _exceptionState.value = ExceptionState.Error(throwable)
        }
    override val ignoreExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            Timber.e(throwable)
        }

    override fun dissmiss() {
        _exceptionState.value = ExceptionState.None
    }

}
