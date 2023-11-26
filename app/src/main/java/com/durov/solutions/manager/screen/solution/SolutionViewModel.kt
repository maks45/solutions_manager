package com.durov.solutions.manager.screen.solution

import androidx.lifecycle.viewModelScope
import com.durov.solutions.manager.core.BaseViewModel
import com.durov.solutions.manager.domain.factor.FactorRepository
import com.durov.solutions.manager.domain.solution.SolutionRepository
import com.durov.solutions.manager.model.Factor
import com.durov.solutions.manager.model.Solution
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class SolutionViewModel(
    private val solutionRepository: SolutionRepository,
    private val factorRepository: FactorRepository
) : BaseViewModel() {
    private val _solutionState = MutableStateFlow<Solution?>(null)
    val solutionState = _solutionState.asStateFlow()

    private val _factorsState = MutableStateFlow<List<Factor>>(emptyList())
    val factorsState = _factorsState.asStateFlow()

    fun loadFactors(subjectId: Long) {
        viewModelScope.launch(mainExceptionHandler) {
            _factorsState.value = factorRepository.getBySubjectId(subjectId)
        }
    }

    fun loadSolution(id: Long) {
        viewModelScope.launch(mainExceptionHandler) {
            _solutionState.value = solutionRepository.loadSolution(id)
        }
    }


    fun changeName(name: String) {
        _solutionState.value = solutionState.value?.copy(
            name = name
        )
    }

    fun saveSolution() {
        solutionState.value?.let {
            if (it.name.isNotBlank()) {
                Timber.e("save solution: $it")
                viewModelScope.launch(ignoreExceptionHandler) {
                    solutionRepository.saveSolution(it)
                }
            }
        }
    }

    fun setFactorRate(factorId: Long, rate: Int) {
        solutionState.value?.let {
            _solutionState.value = it.copy(
                factorsRate = it.factorsRate.toMutableMap().apply {
                    this[factorId] = rate
                }
            )
        }
    }
}