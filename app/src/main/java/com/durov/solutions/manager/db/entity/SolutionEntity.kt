package com.durov.solutions.manager.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.durov.solutions.manager.model.Solution


@Entity(tableName = "solution")
data class SolutionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo(name = "subject_id")
    val subjectId: Long,
    val name: String,
    val factorsRate: Map<Long, Int>,
    val rate: Int?
)

fun SolutionEntity.toSolution() = Solution(
    id = id,
    subjectId = subjectId,
    name = name,
    rate = rate,
    factorsRate = factorsRate
)
