package com.durov.solutions.manager.screen.factor

import androidx.lifecycle.viewModelScope
import com.durov.solutions.manager.core.BaseViewModel
import com.durov.solutions.manager.domain.factor.FactorRepository
import com.durov.solutions.manager.model.Factor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FactorViewModel(private val factorRepository: FactorRepository) : BaseViewModel() {
    private val _factorState = MutableStateFlow<Factor?>(null)
    val factorState = _factorState.asStateFlow()
    fun loadFactor(id: Long) {
        viewModelScope.launch(mainExceptionHandler) {
            _factorState.value = factorRepository.loadFactor(id)
        }
    }

    fun saveFactor(factor: Factor) {
        viewModelScope.launch(ignoreExceptionHandler) {
            factorRepository.saveFactor(factor)
        }
    }
}