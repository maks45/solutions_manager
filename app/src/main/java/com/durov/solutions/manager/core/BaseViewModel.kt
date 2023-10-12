package com.durov.solutions.manager.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.durov.solutions.manager.domain.navigation.NavigationRepository
import com.durov.solutions.manager.navigation.Screen
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

abstract class BaseViewModel : ViewModel(), KoinComponent {
    private val navigationRepository: NavigationRepository = get()
    protected val currentScreenState = navigationRepository.screenState
    protected val finishApp = navigationRepository.finishApp

    fun back() {
        viewModelScope.launch {
            navigationRepository.back()
        }
    }

    fun navigate(screen: Screen) {
        navigationRepository.navigate(screen)
    }

}