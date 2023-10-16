package com.durov.solutions.manager.screen.factor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.durov.solutions.manager.R
import com.durov.solutions.manager.screen.toolbar.SMToolbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel

@Composable
fun ScreenFactor(
    viewModel: FactorViewModel = getViewModel(),
    id: Long
) {
    val factorState = remember { mutableStateOf(viewModel.factorState.value) }
    LaunchedEffect(key1 = Unit) {
        viewModel.loadFactor(id)
        viewModel.factorState.onEach {
            factorState.value = it
        }.collect()
    }
    DisposableEffect(key1 = Unit) {
        onDispose {
            factorState.value?.let {
                viewModel.saveFactor(it)
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        SMToolbar(
            titleRes = R.string.screen_factor_title, onBack = viewModel::back
        )
        Column(Modifier.padding(10.dp)) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                value = factorState.value?.name ?: "",
                label = { Text(stringResource(id = R.string.factor_enter_name)) },
                onValueChange = {
                    factorState.value = factorState.value?.copy(name = it)
                }
            )
            Button(onClick = viewModel::back) {
                Text(text = stringResource(id = R.string.factor_button_done))
            }
        }
    }
}

