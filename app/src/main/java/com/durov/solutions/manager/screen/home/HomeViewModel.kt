package com.durov.solutions.manager.screen.home

import androidx.lifecycle.viewModelScope
import com.durov.solutions.manager.core.BaseViewModel
import com.durov.solutions.manager.domain.subject.SubjectRepository
import com.durov.solutions.manager.model.Subject
import com.durov.solutions.manager.navigation.Dialog
import com.durov.solutions.manager.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class HomeViewModel(val subjectsRepository: SubjectRepository) : BaseViewModel() {
    companion object {
        const val OPEN_SUBJECT_TIMEOUT = 3_000
    }

    private val _subjectsState = MutableStateFlow<List<Subject>>(emptyList())
    val subjectsState = _subjectsState.asStateFlow()
    fun loadAllSubjects() {
        viewModelScope.launch(mainExceptionHandler) {
            _subjectsState.value = subjectsRepository.getAllSubjects()
        }
    }

    fun removeSubjects(subjects: List<Subject>) {
        viewModelScope.launch(mainExceptionHandler) {
            subjects.onEach {
                subjectsRepository.deleteSubject(it)
            }
            _subjectsState.value = subjectsRepository.getAllSubjects()
        }
    }

    fun removeSubject(subject: Subject) {
        viewModelScope.launch(mainExceptionHandler) {
            subjectsRepository.deleteSubject(subject)
            _subjectsState.value = subjectsRepository.getAllSubjects()
        }
    }

    fun editSubject(subject: Subject) {
        subject.id?.let {
            navigate(Screen.Subject(it))
        }
    }

    fun addNewSubject() {
        viewModelScope.launch(mainExceptionHandler) {
            showDialog(Dialog.Loading)
            val id = withTimeout(OPEN_SUBJECT_TIMEOUT.toLong()) {
                subjectsRepository.addSubject(Subject())
            }
            clearDialogs()
            navigate(Screen.Subject(id))
        }
    }

}
