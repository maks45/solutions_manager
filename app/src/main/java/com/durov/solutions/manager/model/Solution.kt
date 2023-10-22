package com.durov.solutions.manager.model

import com.durov.solutions.manager.db.entity.SolutionEntity

data class Solution(
    val id: Long? = null,
    val subjectId: Long,
    val name: String = "",
    val factorsRate: Map<Long, Int> = emptyMap(),
    val rate: Int? = null,
)

fun Solution.toEntity() = SolutionEntity(
    id = id,
    subjectId = subjectId,
    name = name,
    factorsRate = factorsRate,
    rate = rate
)
