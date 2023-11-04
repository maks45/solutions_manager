package com.durov.solutions.manager.domain.navigation

import com.durov.solutions.manager.navigation.Dialog
import com.durov.solutions.manager.navigation.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.LinkedList

class NavigationRepositoryImpl : NavigationRepository {
    private val screenChain = LinkedList<Screen>()
    private val _finishApp = MutableSharedFlow<Unit>()
    private val _screenState = MutableStateFlow<Screen>(Screen.Home)
    private val _dialogState = MutableStateFlow<Dialog>(Dialog.None)
    override val dialogState: StateFlow<Dialog> = _dialogState.asStateFlow()
    override val finishApp: Flow<Unit> = _finishApp.asSharedFlow()
    override val screenState: StateFlow<Screen> = _screenState.asStateFlow()

    override suspend fun back() {
        if (dialogState.value != Dialog.None) {
            clearDialogs()
            return
        }
        when (screenChain.size) {
            0 -> _finishApp.emit(Unit)
            else -> {
                screenChain.removeLast()
                _screenState.value = screenChain.lastOrNull() ?: Screen.Home
            }
        }
    }

    override fun navigate(screen: Screen) {
        screenChain.add(screen)
        _screenState.value = screenChain.last
    }

    override fun replace(screen: Screen) {
        screenChain.removeLast()
        screenChain.addLast(screen)
        _screenState.value = screenChain.last
    }

    override fun showDialog(dialog: Dialog) {
        _dialogState.value = dialog
    }

    override fun clearDialogs() {
        _dialogState.value = Dialog.None
    }

}