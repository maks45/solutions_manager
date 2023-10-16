package com.durov.solutions.manager.domain.factor

import com.durov.solutions.manager.model.Factor

interface FactorRepository {
    suspend fun loadFactor(id: Long): Factor

    suspend fun getBySubjectId(id: Long): List<Factor>

    suspend fun saveFactor(factor: Factor): Long

    suspend fun remove(factor: Factor)

}