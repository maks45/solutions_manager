package com.durov.solutions.manager.domain.solution

import com.durov.solutions.manager.model.Solution

interface SolutionRepository {
    suspend fun loadSolution(id: Long): Solution

    suspend fun getBySubjectId(id: Long): List<Solution>

    suspend fun saveSolution(solution: Solution): Long

    suspend fun remove(solution: Solution)

}