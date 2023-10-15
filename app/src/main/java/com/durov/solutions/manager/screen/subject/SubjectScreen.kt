package com.durov.solutions.manager.screen.subject

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.durov.solutions.manager.R
import com.durov.solutions.manager.model.Subject
import com.durov.solutions.manager.screen.error.ExceptionHandleScreen
import com.durov.solutions.manager.screen.toolbar.SMToolbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel

@Composable
fun SubjectScreen(viewModel: SubjectViewModel = getViewModel(), id: Long?) {
    ExceptionHandleScreen(viewModel) { onErrorDissmiss ->
        onErrorDissmiss.invoke(viewModel::back)
        LaunchedEffect(key1 = Unit) {
            viewModel.loadSubject(id)
        }
        val subjectState = remember { mutableStateOf(Subject()) }
        Box(Modifier.fillMaxSize()) {
            val titleRes = if (id == null) {
                R.string.screen_subject_title_add
            } else {
                R.string.screen_subject_title_edit
            }
            Column {
                SMToolbar(
                    titleRes = titleRes,
                    onBack = viewModel::back
                )
                SubjectStateScreen(subjectState = subjectState)
            }
            Button(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(all = 20.dp),
                onClick = { viewModel.saveSubject(subjectState.value) }
            ) {
                Text(
                    text = stringResource(R.string.button_save_subject),
                    style = TextStyle()
                )
            }
        }
    }
}

@Composable
private fun SubjectStateScreen(
    modifier: Modifier = Modifier,
    subjectState: MutableState<Subject>,
    viewModel: SubjectViewModel = getViewModel()
) {
    val showLoaderState = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        viewModel.screenState.onEach {
            when (it) {
                is SubjectScreenState.StartLoading -> showLoaderState.value = true
                is SubjectScreenState.UpdateState -> subjectState.value = it.subject
                is SubjectScreenState.FinishLoading -> showLoaderState.value = false
            }
        }.collect()
    }
    Box(modifier = modifier.fillMaxSize()) {
        EditSubjectScreen(subjectState = subjectState)
        if (showLoaderState.value) LoadingScreen()
    }
}

@Composable
private fun EditSubjectScreen(
    subjectState: MutableState<Subject>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = subjectState.value.name,
            label = { Text(stringResource(id = R.string.screen_subject_hint_name)) },
            onValueChange = {
                subjectState.value = subjectState.value.copy(name = it)
            }
        )
    }
}

@Composable
private fun LoadingScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}