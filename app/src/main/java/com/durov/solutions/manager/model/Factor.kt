package com.durov.solutions.manager.model

import com.durov.solutions.manager.db.entity.FactorEntity

data class Factor(
    val id: Long? = null,
    val subjectId: Long?,
    val name: String = "",
    val priority: Int? = null,
)

fun Factor.toFactorEntity() = FactorEntity(
    id = id,
    subjectId = subjectId,
    name = name,
    priority = priority
)
