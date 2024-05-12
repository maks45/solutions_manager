package com.durov.solutions.manager.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
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
    val selectedSubjects = remember { mutableStateListOf<Subject>() }
    val deleteModeState = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        viewModel.loadAllSubjects()
        viewModel.subjectsState.onEach {
            subjectsState.value = it
        }.collect()
    }

    fun onSubjectSelected(subject: Subject) {
        if (selectedSubjects.contains(subject)) {
            selectedSubjects.remove(subject)
        } else {
            selectedSubjects.add(subject)
        }
        deleteModeState.value = selectedSubjects.isNotEmpty()
    }

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            SMHomeToolbar(
                deleteMode = deleteModeState,
                onDelete = {
                    viewModel.removeSubjects(selectedSubjects)
                    deleteModeState.value = false
                }
            )
            SubjectItemsList(
                subjectItems = subjectsState.value,
                isDeleteMode = deleteModeState.value,
                selectedSubjects = selectedSubjects,
                onSubjectSelected = ::onSubjectSelected
            )
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
    subjectItems: List<Subject>,
    isDeleteMode: Boolean,
    selectedSubjects: List<Subject>,
    onSubjectSelected: (Subject) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(5.dp)
            .verticalScroll(rememberScrollState())
    ) {
        subjectItems.forEach {
            SubjectItemCard(
                subject = it,
                isSelected = selectedSubjects.contains(it),
                onSubjectSelected = onSubjectSelected,
                isDeleteMode = isDeleteMode
            )
        }
        Spacer(modifier = Modifier.height(70.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SubjectItemCard(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    isDeleteMode: Boolean,
    subject: Subject,
    onSubjectSelected: (Subject) -> Unit,
    viewModel: HomeViewModel = getViewModel(),
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .defaultMinSize(minHeight = 30.dp)
            .combinedClickable(
                onClick = { viewModel.editSubject(subject) },
                onLongClick = { onSubjectSelected.invoke(subject) }
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .requiredHeight(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = subject.name,
            )
            if (isDeleteMode) {
                IconButton(
                    modifier = Modifier.padding(10.dp),
                    onClick = {
                        onSubjectSelected.invoke(subject)
                    }) {
                    val iconRes = if (isSelected) {
                        R.drawable.ic_check_box_checked
                    } else {
                        R.drawable.ic_check_box_blank
                    }
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = "delete_subject_item"
                    )
                }
            }
        }
    }
}