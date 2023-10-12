package com.durov.solutions.manager.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    LaunchedEffect(key1 = Unit) {
        viewModel.finishAppFlow.onEach {
            Timber.d("Close application")
            (context as? MainActivity)?.finish()
        }.collect()
    }
    BackHandler(true, viewModel::back)
    Box(modifier = Modifier.fillMaxSize()) {
        when (screenState.value) {

            else -> Unit
        }
    }
}