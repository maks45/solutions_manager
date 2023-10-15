package com.durov.solutions.manager.domain.subject

import com.durov.solutions.manager.model.Subject

interface SubjectRepository {
    suspend fun getAllSubjects(): List<Subject>

    suspend fun updateSubject(subject: Subject)

    suspend fun deleteSubject(subject: Subject)

    suspend fun getById(id: Long): Subject

}