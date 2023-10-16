package com.durov.solutions.manager.screen.subject

import androidx.lifecycle.viewModelScope
import com.durov.solutions.manager.core.BaseViewModel
import com.durov.solutions.manager.domain.factor.FactorRepository
import com.durov.solutions.manager.domain.solution.SolutionRepository
import com.durov.solutions.manager.domain.subject.SubjectRepository
import com.durov.solutions.manager.model.Factor
import com.durov.solutions.manager.model.Solution
import com.durov.solutions.manager.model.Subject
import com.durov.solutions.manager.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SubjectViewModel(
    private val subjectRepository: SubjectRepository,
    private val solutionRepository: SolutionRepository,
    private val factorRepository: FactorRepository
) : BaseViewModel() {
    private val _subjectState = MutableStateFlow<Subject?>(null)
    val subjectState = _subjectState.asStateFlow()

    fun loadSubject(id: Long) {
        viewModelScope.launch(mainExceptionHandler) {
            _subjectState.value = subjectRepository.loadById(id)
        }
    }

    fun saveSubject(subject: Subject) {
        viewModelScope.launch(mainExceptionHandler) {
            subjectRepository.updateSubject(subject)
        }
    }

    fun editSolution(solution: Solution) {
        viewModelScope.launch(mainExceptionHandler) {
            val id = solution.id ?: solutionRepository.saveSolution(solution)
            navigate(Screen.Solution(id))
        }
    }

    fun removeSolution(solution: Solution) {
        viewModelScope.launch(mainExceptionHandler) {
            solutionRepository.remove(solution)
            solution.subjectId?.let {
                _subjectState.value = subjectRepository.loadById(solution.subjectId)
            }
        }
    }

    fun editFactor(factor: Factor) {
        viewModelScope.launch(mainExceptionHandler) {
            val id = factor.id ?: factorRepository.saveFactor(factor)
            navigate(Screen.Factor(id))
        }
    }

    fun removeFactor(factor: Factor) {
        viewModelScope.launch(mainExceptionHandler) {
            factorRepository.remove(factor)
            factor.subjectId?.let {
                _subjectState.value = subjectRepository.loadById(factor.subjectId)
            }
        }
    }

}
