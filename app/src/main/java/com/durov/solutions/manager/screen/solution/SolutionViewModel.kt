package com.durov.solutions.manager.screen.solution

import androidx.lifecycle.viewModelScope
import com.durov.solutions.manager.core.BaseViewModel
import com.durov.solutions.manager.domain.solution.SolutionRepository
import com.durov.solutions.manager.model.Solution
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SolutionViewModel(private val solutionRepository: SolutionRepository) : BaseViewModel() {
    private val _solutionState = MutableStateFlow<Solution?>(null)
    val solutionState = _solutionState.asStateFlow()
    fun loadSolution(id: Long) {
        viewModelScope.launch(mainExceptionHandler) {
            _solutionState.value = solutionRepository.loadSolution(id)
        }
    }

    fun saveSolution(solution: Solution) {
        viewModelScope.launch(ignoreExceptionHandler) {
            solutionRepository.saveSolution(solution)
        }
    }
}