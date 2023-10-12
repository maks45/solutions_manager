package com.durov.solutions.manager.core

import com.durov.solutions.manager.model.Subject

interface SubjectRepository {
    suspend fun getAllSubjects(): List<Subject>

    suspend fun updateSubject(subject: Subject)

    suspend fun deleteSubject(subject: Subject)

}