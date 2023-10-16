package com.durov.solutions.manager.model

import com.durov.solutions.manager.db.entity.SolutionEntity

data class Solution(
    val id: Long? = null,
    val subjectId: Long? = null,
    val name: String = "",
    val rate: Int? = null
)

fun Solution.toEntity() = SolutionEntity(
    id = id,
    subjectId = subjectId,
    name = name,
    rate = rate
)
