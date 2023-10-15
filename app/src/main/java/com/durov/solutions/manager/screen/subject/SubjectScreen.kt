package com.durov.solutions.manager.screen.subject

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.durov.solutions.manager.R
import com.durov.solutions.manager.model.Subject
import com.durov.solutions.manager.screen.error.ExceptionHandleScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun SubjectScreen(viewModel: SubjectViewModel = getViewModel(), id: Long?) {
    ExceptionHandleScreen(viewModel) { onErrorDissmiss ->
        onErrorDissmiss.invoke { viewModel.loadSubject(null) }
        LaunchedEffect(key1 = Unit) {
            viewModel.loadSubject(id)
        }
        Box(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            SubjectStateScreen()
            Button(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(all = 10.dp),
                onClick = {}
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
    viewModel: SubjectViewModel = getViewModel()
) {
    val screenState = viewModel.screenState.collectAsState()
    when (val screen = screenState.value) {
        is SubjectScreenState.LoadingState -> LoadingScreen()
        is SubjectScreenState.SubjectState -> EditSubjectScreen(screen.subject)
    }
}

@Composable
private fun EditSubjectScreen(subject: Subject, viewModel: SubjectViewModel = getViewModel()) {
    //todo
}

@Composable
private fun LoadingScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}