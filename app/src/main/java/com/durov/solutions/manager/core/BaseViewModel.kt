package com.durov.solutions.manager.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.durov.solutions.manager.domain.navigation.NavigationRepository
import com.durov.solutions.manager.navigation.Dialog
import com.durov.solutions.manager.navigation.Screen
import com.durov.solutions.manager.screen.error.ExceptionHandler
import com.durov.solutions.manager.screen.error.model.ExceptionState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

abstract class BaseViewModel :
    ViewModel(),
    KoinComponent,
    ExceptionHandler {
    private val navigationRepository: NavigationRepository = get()
    private val exceptionHandler: ExceptionHandler = get()
    protected val currentScreenState = navigationRepository.screenState
    protected val currentDialogState = navigationRepository.dialogState
    protected val finishApp = navigationRepository.finishApp

    override val exceptionState: StateFlow<ExceptionState> = exceptionHandler.exceptionState
    override val ignoreExceptionHandler: CoroutineExceptionHandler =
        exceptionHandler.ignoreExceptionHandler
    override val mainExceptionHandler: CoroutineExceptionHandler =
        exceptionHandler.mainExceptionHandler

    override fun dissmiss() = exceptionHandler.dissmiss()

    fun back() {
        viewModelScope.launch {
            navigationRepository.back()
        }
    }

    fun navigate(screen: Screen) {
        navigationRepository.navigate(screen)
    }

    fun showDialog(dialog: Dialog) {
        navigationRepository.showDialog(dialog)
    }

    fun clearDialogs() {
        navigationRepository.clearDialogs()
    }


}