package com.durov.solutions.manager.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "solution")
data class SolutionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String
)
