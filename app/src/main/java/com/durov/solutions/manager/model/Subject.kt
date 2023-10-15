package com.durov.solutions.manager.model

import com.durov.solutions.manager.db.entity.SubjectEntity

data class Subject(
    val id: Long? = null,
    val name: String = "",
    val factors: List<Factor> = emptyList(),
    val solutions: List<Solution> = emptyList()
)

fun Subject.toEntity(): SubjectEntity {
    return SubjectEntity(
        id = id,
        name = name,
        factors = factors,
        solutions = solutions
    )
}
