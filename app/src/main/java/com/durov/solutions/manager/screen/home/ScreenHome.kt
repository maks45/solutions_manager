package com.durov.solutions.manager.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.durov.solutions.manager.R
import com.durov.solutions.manager.model.Subject
import com.durov.solutions.manager.screen.toolbar.SMHomeToolbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel

@Composable
fun ScreenHome(viewModel: HomeViewModel = getViewModel()) {
    val subjectsState = remember { mutableStateOf<List<Subject>>(emptyList()) }
    LaunchedEffect(key1 = Unit) {
        viewModel.loadAllSubjects()
        viewModel.subjectsState.onEach {
            subjectsState.value = it
        }.collect()
    }
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            SMHomeToolbar()
            SubjectItemsList(subjectItems = subjectsState.value)
        }
        Button(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(all = 20.dp),
            onClick = viewModel::addNewSubject
        ) {
            Text(
                text = stringResource(R.string.button_add_new_subject),
                style = TextStyle()
            )
        }
    }
}

@Composable
fun SubjectItemsList(
    modifier: Modifier = Modifier,
    subjectItems: List<Subject>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(5.dp)
            .verticalScroll(rememberScrollState())
    ) {
        subjectItems.forEach {
            SubjectItemCard(subject = it)
        }
        Spacer(modifier = Modifier.height(70.dp))
    }
}

@Composable
private fun SubjectItemCard(
    modifier: Modifier = Modifier,
    subject: Subject,
    viewModel: HomeViewModel = getViewModel(),
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .defaultMinSize(minHeight = 30.dp)
            .clickable {
                viewModel.editSubject(subject)
            },
        shape = RoundedCornerShape(5.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = subject.name,
            )
            IconButton(onClick = {
                viewModel.removeSubject(subject)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "delete_subject_item"
                )
            }
        }
    }
}