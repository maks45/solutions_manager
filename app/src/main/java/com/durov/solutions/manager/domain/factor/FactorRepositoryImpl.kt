package com.durov.solutions.manager.domain.factor

import com.durov.solutions.manager.db.FactorDao
import com.durov.solutions.manager.db.entity.toFactor
import com.durov.solutions.manager.model.Factor
import com.durov.solutions.manager.model.toFactorEntity

class FactorRepositoryImpl(private val factorDao: FactorDao) : FactorRepository {
    override suspend fun loadFactor(id: Long): Factor {
        return factorDao.getById(id).toFactor()
    }

    override suspend fun getBySubjectId(id: Long): List<Factor> {
        return factorDao.getBySubjectId(id).map { it.toFactor() }
    }

    override suspend fun saveFactor(factor: Factor): Long {
        return factorDao.addFactor(factor.toFactorEntity())
    }

    override suspend fun remove(factor: Factor) {
        factorDao.deleteFactor(factor.toFactorEntity())
    }

}