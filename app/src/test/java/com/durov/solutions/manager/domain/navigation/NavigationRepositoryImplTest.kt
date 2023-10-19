package com.durov.solutions.manager.domain.navigation

import com.durov.solutions.manager.navigation.Dialog
import com.durov.solutions.manager.navigation.Screen
import kotlinx.coroutines.runBlocking
import org.junit.Test

class NavigationRepositoryImplTest {

    private val repository = NavigationRepositoryImpl()

    @Test
    fun back() {
        repository.navigate(Screen.Subject(0))
        repository.navigate(Screen.Factor(1))
        repository.navigate(Screen.Home)
        repository.navigate(Screen.Subject(0))
        runBlocking {
            repository.back()
            assert(repository.screenState.value is Screen.Home)
            repository.back()
            assert(repository.screenState.value is Screen.Factor)
            repository.back()
            assert(repository.screenState.value is Screen.Subject)
        }
    }

    @Test
    fun navigate() {
        repository.navigate(Screen.Factor(0))
        assert(repository.screenState.value is Screen.Factor)
    }

    @Test
    fun replace() {
        repository.navigate(Screen.Home)
        repository.navigate(Screen.Factor(0))
        repository.replace(Screen.Subject(0))
        runBlocking {
            repository.back()
            assert(repository.screenState.value is Screen.Home)
        }
    }

    @Test
    fun showDialog() {
        repository.showDialog(Dialog.Loading)
        assert(repository.dialogState.value is Dialog.Loading)
    }

    @Test
    fun clearDialogs() {
        repository.showDialog(Dialog.Loading)
        repository.showDialog(Dialog.Error(Exception()))
        repository.showDialog(Dialog.Loading)
        repository.clearDialogs()
        assert(repository.dialogState.value is Dialog.None)
    }

}