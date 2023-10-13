package com.durov.solutions.manager.domain.navigation

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
    override val finishApp: Flow<Unit> = _finishApp.asSharedFlow()
    override val screenState: StateFlow<Screen> = _screenState.asStateFlow()

    override suspend fun back() {
        when (screenState.value) {
            Screen.Home -> _finishApp.emit(Unit)
            else -> {
                screenChain.poll()
                _screenState.value = screenChain.lastOrNull() ?: Screen.Home
            }
        }
    }

    override fun navigate(screen: Screen) {
        screenChain.add(screen)
        _screenState.value = screenChain.last
    }

    override fun replace(screen: Screen) {
        screenChain.addLast(screen)
        _screenState.value = screenChain.last
    }

}