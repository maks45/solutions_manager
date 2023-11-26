package com.durov.solutions.manager.screen.solution

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.durov.solutions.manager.R
import com.durov.solutions.manager.model.Factor
import com.durov.solutions.manager.model.Solution
import com.durov.solutions.manager.screen.toolbar.SMToolbar
import org.koin.androidx.compose.getViewModel

@Composable
fun ScreenSolution(
    viewModel: SolutionViewModel = getViewModel(),
    subjectId: Long,
    solutionId: Long,

    ) {
    val solutionState = viewModel.solutionState.collectAsState(null)
    val solutionFactors = viewModel.factorsState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadSolution(solutionId)
        viewModel.loadFactors(subjectId)
    }
    DisposableEffect(key1 = Unit) {
        onDispose {
            solutionState.value?.let {
                viewModel.saveSolution()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        SMToolbar(
            titleRes = R.string.screen_solution_title, onBack = viewModel::back
        )
        Column(Modifier.padding(10.dp)) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                value = solutionState.value?.name ?: "",
                label = { Text(stringResource(id = R.string.solution_enter_name)) },
                onValueChange = {
                    viewModel.changeName(it)
                }
            )

            FactorsList(factors = solutionFactors.value, solutionState = solutionState)

            Button(onClick = viewModel::back) {
                Text(text = stringResource(id = R.string.solution_button_done))
            }
        }
    }
}

@Composable
private fun FactorsList(factors: List<Factor>, solutionState: State<Solution?>) {
    factors.forEach {
        FactorItem(factor = it, solutionState = solutionState)
    }
}

@Composable
private fun FactorItem(
    viewModel: SolutionViewModel = getViewModel(),
    factor: Factor,
    solutionState: State<Solution?>
) {
    val rate = solutionState.value?.factorsRate?.get(factor.id) ?: 0
    val factorRate = remember(solutionState.value) { mutableIntStateOf(rate) }
    Text(text = factor.name)
    Row {
        Text(text = stringResource(id = R.string.solution_factor_rate))
        Text(text = "\t${factorRate.intValue}")
    }

    Slider(
        value = factorRate.intValue.toFloat(),
        valueRange = 1f..10f,
        onValueChange = { factorRate.intValue = it.toInt() },
        steps = 10,
        onValueChangeFinished = {
            solutionState.value?.let {
                factor.id?.let {
                    viewModel.setFactorRate(it, factorRate.intValue)
                }
            }
        }
    )
}

