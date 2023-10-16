package com.durov.solutions.manager.domain.solution

import com.durov.solutions.manager.db.SolutionDao
import com.durov.solutions.manager.db.entity.toSolution
import com.durov.solutions.manager.model.Solution
import com.durov.solutions.manager.model.toEntity

class SolutionRepositoryImpl(private val solutionDao: SolutionDao) : SolutionRepository {
    override suspend fun loadSolution(id: Long): Solution {
        return solutionDao.getById(id).toSolution()
    }

    override suspend fun getBySubjectId(id: Long): List<Solution> {
        return solutionDao.getBySubjectId(id).map { it.toSolution() }
    }

    override suspend fun saveSolution(solution: Solution): Long {
        return solutionDao.addSolution(solution.toEntity())
    }

    override suspend fun remove(solution: Solution) {
        solutionDao.deleteSolution(solution.toEntity())
    }

}
