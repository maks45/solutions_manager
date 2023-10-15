package com.durov.solutions.manager.domain.subject

import com.durov.solutions.manager.db.SubjectDao
import com.durov.solutions.manager.db.entity.toSubject
import com.durov.solutions.manager.model.Subject
import com.durov.solutions.manager.model.toEntity

class SubjectRepositoryImpl(
    private val subjectDao: SubjectDao
) : SubjectRepository {
    override suspend fun getAllSubjects(): List<Subject> {
        return subjectDao.getAll().map {
            it.toSubject()
        }
    }

    override suspend fun updateSubject(subject: Subject) {
        subjectDao.addSubject(subject.toEntity())
    }

    override suspend fun deleteSubject(subject: Subject) {
        subjectDao.deleteSubject(subject.toEntity())
    }

    override suspend fun getById(id: Long): Subject {
        return subjectDao.getById(id).toSubject()
    }

}