package com.durov.solutions.manager.screen.loading

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import com.durov.solutions.manager.navigation.NavigationViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun DialogLoading(viewModel: NavigationViewModel = getViewModel()) {
    Dialog(onDismissRequest = viewModel::clearDialogs) {
        CircularProgressIndicator()
    }
}