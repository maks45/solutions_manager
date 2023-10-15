package com.durov.solutions.manager.screen.error

import com.durov.solutions.manager.screen.error.model.ExceptionScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class ExceptionHandlerImpl : ExceptionHandler {
    private val _exceptionState =
        MutableStateFlow<ExceptionScreenState>(ExceptionScreenState.None)
    override val exceptionState: StateFlow<ExceptionScreenState>
        get() = _exceptionState.asStateFlow()
    override val mainExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            _exceptionState.value = ExceptionScreenState.Error(throwable)
        }
    override val ignoreExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            Timber.e(throwable)
        }

    override fun dissmiss() {
        _exceptionState.value = ExceptionScreenState.None
    }

}
