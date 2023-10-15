package com.durov.solutions.manager.screen.subject

import androidx.lifecycle.viewModelScope
import com.durov.solutions.manager.core.BaseViewModel
import com.durov.solutions.manager.domain.subject.SubjectRepository
import com.durov.solutions.manager.model.Subject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SubjectViewModel(
    private val subjectRepository: SubjectRepository
) : BaseViewModel() {
    companion object {
        const val LOADING_DELAY = 2000L
    }

    private val _screenState = MutableStateFlow<SubjectScreenState>(SubjectScreenState.StartLoading)
    val screenState = _screenState.asSharedFlow()

    fun loadSubject(id: Long?) {
        viewModelScope.launch(mainExceptionHandler) {
            val subject = id?.let {
                subjectRepository.getById(it)
            } ?: kotlin.run {
                delay(LOADING_DELAY)
                Subject()
            }
            _screenState.value = SubjectScreenState.UpdateState(subject)
            _screenState.value = SubjectScreenState.FinishLoading
        }
    }

    fun saveSubject(subject: Subject) {
        viewModelScope.launch(mainExceptionHandler) {
            _screenState.value = SubjectScreenState.StartLoading
            subjectRepository.updateSubject(subject)
            delay(LOADING_DELAY)
            _screenState.value = SubjectScreenState.FinishLoading
        }
    }

}
