package com.durov.solutions.manager.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "factor")
data class FactorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String,
    val weight: Int
)
