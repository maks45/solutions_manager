package com.durov.solutions.manager.screen.subject

import androidx.lifecycle.viewModelScope
import com.durov.solutions.manager.core.BaseViewModel
import com.durov.solutions.manager.domain.subject.SubjectRepository
import com.durov.solutions.manager.model.Subject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SubjectViewModel(
    private val subjectRepository: SubjectRepository
) : BaseViewModel() {
    companion object {
        const val LOADING_DELAY = 2000L
    }

    private val _screenState = MutableStateFlow<SubjectScreenState>(SubjectScreenState.LoadingState)
    val screenState = _screenState.asStateFlow()

    fun loadSubject(id: Long?) {
        viewModelScope.launch(mainExceptionHandler) {
            val subject = id?.let {
                subjectRepository.getById(it)
            } ?: kotlin.run {
                delay(LOADING_DELAY)
                Subject()
            }
            _screenState.value = SubjectScreenState.SubjectState(subject)
        }
    }

    fun saveSubject(subject: Subject) {
        viewModelScope.launch(ignoreExceptionHandler) {
            subjectRepository.updateSubject(subject)
        }
    }

}
