package com.durov.solutions.manager.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.durov.solutions.manager.model.Factor
import com.durov.solutions.manager.model.Subject

@Entity(tableName = "subject")
data class SubjectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String,
    val factors: List<Factor>,
   // val solutions: List<Solution>
)

fun SubjectEntity.toSubject(): Subject {
    return Subject(
        id = id,
        name = name,
        factors = factors,
        solutions = emptyList()
    )
}
