package com.durov.solutions.manager.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.durov.solutions.manager.screen.error.DialogError
import com.durov.solutions.manager.screen.factor.ScreenFactor
import com.durov.solutions.manager.screen.home.ScreenHome
import com.durov.solutions.manager.screen.loading.DialogLoading
import com.durov.solutions.manager.screen.solution.ScreenSolution
import com.durov.solutions.manager.screen.subject.ScreenSubject
import com.durov.solutions.manager.ui.MainActivity
import com.durov.solutions.manager.ui.NavigationViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@Composable
fun Navigator(
    viewModel: NavigationViewModel = getViewModel()
) {
    val context = LocalContext.current
    val screenState = viewModel.screenState.collectAsState()
    val dialogState = viewModel.dialogState.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.finishAppFlow.onEach {
            Timber.d("Close application")
            (context as? MainActivity)?.finish()
        }.collect()
    }
    BackHandler(true, viewModel::back)
    Surface(modifier = Modifier.fillMaxSize()) {
        Box {
            when (val state = screenState.value) {
                is Screen.Home -> ScreenHome()
                is Screen.Subject -> ScreenSubject(id = state.id)
                is Screen.Solution -> ScreenSolution(id = state.id)
                is Screen.Factor -> ScreenFactor(id = state.id)
                else -> Unit
            }
            when (val dialog = dialogState.value) {
                is Dialog.Loading -> DialogLoading()
                is Dialog.Error -> DialogError(dialog.cause)
                else -> Unit
            }
        }
    }
}