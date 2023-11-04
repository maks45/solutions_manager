package com.durov.solutions.manager.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.durov.solutions.manager.domain.navigation.NavigationRepository
import com.durov.solutions.manager.navigation.Dialog
import com.durov.solutions.manager.navigation.Screen
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import timber.log.Timber

abstract class BaseViewModel :
    ViewModel(),
    KoinComponent {
    private val navigationRepository: NavigationRepository = get()
    protected val currentScreenState = navigationRepository.screenState
    protected val currentDialogState = navigationRepository.dialogState
    protected val finishApp = navigationRepository.finishApp
    protected val mainExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            Timber.e(throwable)
            showDialog(Dialog.Error(throwable))
        }
    protected val ignoreExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            Timber.e(throwable)
        }

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