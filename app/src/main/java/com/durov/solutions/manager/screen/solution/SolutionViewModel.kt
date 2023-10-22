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

class SolutionViewModel(
    private val solutionRepository: SolutionRepository,
    private val factorRepository: FactorRepository
) : BaseViewModel() {
    private val _solutionState = MutableStateFlow<Solution?>(null)
    val solutionState = _solutionState.asStateFlow()
    private val _factorsState = MutableStateFlow<List<Factor>>(emptyList())
    val factorsState = _factorsState.asStateFlow()

    fun loadFactors(id: Long) {
        viewModelScope.launch(mainExceptionHandler) {
            _factorsState.value = factorRepository.getBySubjectId(id)
        }
    }

    fun loadSolution(id: Long) {
        viewModelScope.launch(mainExceptionHandler) {
            _solutionState.value = solutionRepository.loadSolution(id)
        }
    }

    fun saveSolution(solution: Solution) {
        viewModelScope.launch(ignoreExceptionHandler) {
            if (solution.name.isNotBlank()) {
                solutionRepository.saveSolution(solution)
            } else {
                solutionRepository.remove(solution)
            }
        }
    }
}