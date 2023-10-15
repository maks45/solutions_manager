package com.durov.solutions.manager.screen.error

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.durov.solutions.manager.screen.error.model.ExceptionScreenState

@Composable
fun ExceptionHandleScreen(
    handler: ExceptionHandler,
    content: @Composable ((() -> Unit) -> Unit) -> Unit = {}
) {
    val dissmissAction = remember { mutableStateOf({}) }
    fun onDissmiss() {
        dissmissAction.value.invoke()
        handler.dissmiss()
    }

    fun addDissmissAction(action: () -> Unit) {
        dissmissAction.value = action
    }

    val screenState = handler.exceptionState.collectAsState()
    when (val state = screenState.value) {
        is ExceptionScreenState.Error -> ErrorDialog(cause = state.cause, onDissmiss = ::onDissmiss)
        else -> Unit
    }
    content.invoke(::addDissmissAction)

}