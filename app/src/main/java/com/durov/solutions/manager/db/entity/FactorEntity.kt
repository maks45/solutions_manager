package com.durov.solutions.manager.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.durov.solutions.manager.model.Factor

@Entity(tableName = "factor")
data class FactorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo(name = "subject_id")
    val subjectId: Long?,
    val name: String,
    val priority: Int?
)

fun FactorEntity.toFactor() = Factor(
    id = id,
    name = name,
    subjectId = subjectId,
    priority = priority,
)
