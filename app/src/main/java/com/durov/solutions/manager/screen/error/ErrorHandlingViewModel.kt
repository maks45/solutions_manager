package com.durov.solutions.manager.screen.error

import androidx.lifecycle.viewModelScope
import com.durov.solutions.manager.core.BaseViewModel
import com.durov.solutions.manager.navigation.Dialog
import com.durov.solutions.manager.screen.error.model.ExceptionState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ErrorHandlingViewModel(handler: ExceptionHandler) : BaseViewModel() {
    init {
        handler.exceptionState.onEach {
            when (it) {
                is ExceptionState.Error -> showDialog(Dialog.Error(it.cause))
                is ExceptionState.None -> clearDialogs()
            }
        }.launchIn(viewModelScope)
    }

}