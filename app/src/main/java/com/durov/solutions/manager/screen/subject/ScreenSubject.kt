package com.durov.solutions.manager.screen.subject

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.durov.solutions.manager.R
import com.durov.solutions.manager.model.Factor
import com.durov.solutions.manager.model.Solution
import com.durov.solutions.manager.model.Subject
import com.durov.solutions.manager.screen.toolbar.SMToolbar
import com.durov.solutions.manager.util.visible
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@Composable
fun ScreenSubject(viewModel: SubjectViewModel = getViewModel(), id: Long) {
    val subjectState = remember { mutableStateOf(viewModel.subjectState.value) }
    LaunchedEffect(key1 = Unit) {
        viewModel.loadSubject(id)
        viewModel.subjectState.onEach {
            Timber.d("Loaded subject ->$it")
            subjectState.value = it
        }.collect()
    }
    DisposableEffect(key1 = Unit) {
        onDispose {
            subjectState.value?.let {
                viewModel.saveSubject(it)
            }
        }
    }
    Column(Modifier.fillMaxSize()) {
        SMToolbar(
            titleRes = R.string.screen_subject_title, onBack = viewModel::back
        )
        SubjectStateScreen(subjectState = subjectState, subjectId = id)
        Button(
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 10.dp, bottom = 20.dp, end = 20.dp),
            onClick = viewModel::calculateOptimal
        ) {
            Text(
                text = stringResource(R.string.button_calculate_solution), style = TextStyle()
            )
        }
    }
}

@Composable
private fun ColumnScope.SubjectStateScreen(
    modifier: Modifier = Modifier,
    subjectState: MutableState<Subject?>,
    subjectId: Long
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .weight(1f)
    ) {
        EditSubjectScreen(subjectState = subjectState, subjectId)
    }
}

@Composable
private fun EditSubjectScreen(
    subjectState: MutableState<Subject?>,
    subjectId: Long
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TextField(modifier = Modifier.fillMaxWidth(),
            value = subjectState.value?.name ?: "",
            label = { Text(stringResource(id = R.string.screen_subject_enter_name)) },
            onValueChange = {
                subjectState.value = subjectState.value?.copy(name = it)
            })
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            text = stringResource(id = R.string.screen_subject_factors_title)
        )
        FactorsList(
            factors = subjectState.value?.factors ?: emptyList(),
            subjectId = subjectId
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            text = stringResource(id = R.string.screen_subject_solutions_title)
        )
        SolutionsList(
            solutions = subjectState.value?.solutions ?: emptyList(),
            subjectId = subjectId
        )
    }
}

@Composable
private fun SolutionsList(
    modifier: Modifier = Modifier,
    subjectId: Long,
    viewModel: SubjectViewModel = getViewModel(),
    solutions: List<Solution>
) {

    Column(modifier) {
        solutions.onEach {
            SolutionItemCard(solution = it)
        }
    }
    Button(onClick = { viewModel.editSolution(Solution(subjectId = subjectId)) }) {
        Text(
            text = stringResource(R.string.button_subject_add_solution),
        )
    }
}

@Composable
private fun FactorsList(
    modifier: Modifier = Modifier,
    subjectId: Long,
    viewModel: SubjectViewModel = getViewModel(),
    factors: List<Factor>
) {
    Column(modifier) {
        factors.onEach {
            FactorItemCard(factor = it)
        }
    }
    Button(onClick = {
        viewModel.editFactor(Factor(subjectId = subjectId))
    }) {
        Text(
            text = stringResource(R.string.button_subject_add_factor),
        )
    }
}

@Composable
private fun FactorItemCard(
    modifier: Modifier = Modifier,
    factor: Factor,
    viewModel: SubjectViewModel = getViewModel(),
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .defaultMinSize(minHeight = 30.dp)
            .clickable {
                viewModel.editFactor(factor)
            },
        shape = RoundedCornerShape(5.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f),
                text = factor.name,
            )
            IconButton(onClick = {
                viewModel.removeFactor(factor)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "delete_subject_item"
                )
            }
        }
    }
}

@Composable
private fun SolutionItemCard(
    modifier: Modifier = Modifier,
    solution: Solution,
    viewModel: SubjectViewModel = getViewModel(),
) {
    val optimalSolution = viewModel.optimalSolutionIdState.collectAsState()
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .defaultMinSize(minHeight = 30.dp)
            .clickable {
                viewModel.editSolution(solution)
            },
        shape = RoundedCornerShape(5.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f),
                text = solution.name,
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .visible(optimalSolution.value == solution.id),
                text = stringResource(id = R.string.subject_optimal_solution),
                style = TextStyle(color = Color.Green)
            )
            IconButton(onClick = {
                viewModel.removeSolution(solution)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "delete_subject_item"
                )
            }
        }
    }
}
