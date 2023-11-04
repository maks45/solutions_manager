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
import timber.log.Timber

class SubjectViewModel(
    private val subjectRepository: SubjectRepository,
    private val solutionRepository: SolutionRepository,
    private val factorRepository: FactorRepository
) : BaseViewModel() {
    private val _subjectState = MutableStateFlow<Subject?>(null)
    val subjectState = _subjectState.asStateFlow()
    private val _optimalSolutionIdState = MutableStateFlow<Long?>(null)
    val optimalSolutionIdState = _optimalSolutionIdState.asStateFlow()

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
        removeOptimal()
        viewModelScope.launch(mainExceptionHandler) {
            val id = solution.id ?: solutionRepository.saveSolution(solution)
            navigate(Screen.Solution(subjectId = solution.subjectId, solutionId = id))
        }
    }

    fun removeSolution(solution: Solution) {
        removeOptimal()
        viewModelScope.launch(mainExceptionHandler) {
            solutionRepository.remove(solution)
            _subjectState.value = subjectRepository.loadById(solution.subjectId)
        }
    }

    fun editFactor(factor: Factor) {
        removeOptimal()
        viewModelScope.launch(mainExceptionHandler) {
            val id = factor.id ?: factorRepository.saveFactor(factor)
            navigate(Screen.Factor(id))
        }
    }

    fun removeFactor(factor: Factor) {
        removeOptimal()
        viewModelScope.launch(mainExceptionHandler) {
            factorRepository.remove(factor)
            _subjectState.value = subjectRepository.loadById(factor.subjectId)
        }
    }

    private fun removeOptimal() {
        _optimalSolutionIdState.value = null
    }

    fun calculateOptimal() {
        subjectState.value?.let { subject ->
            var solutionRate = 0
            var optimalId: Long? = null
            subject.solutions.forEach { solution ->
                val rate = solution.factorsRate.entries.sumOf { rate ->
                    subject.factors.first { it.id == rate.key }.priority * rate.value
                }
                Timber.e(rate.toString())
                if (rate > solutionRate) {
                    solutionRate = rate
                    optimalId = solution.id
                }
            }
            _optimalSolutionIdState.value = optimalId
        }
    }

}
