package com.durov.solutions.manager.domain.subject

import com.durov.solutions.manager.db.SubjectDao
import com.durov.solutions.manager.db.entity.toSubject
import com.durov.solutions.manager.domain.factor.FactorRepository
import com.durov.solutions.manager.domain.solution.SolutionRepository
import com.durov.solutions.manager.model.Subject
import com.durov.solutions.manager.model.toEntity

class SubjectRepositoryImpl(
    private val subjectDao: SubjectDao,
    private val factorRepository: FactorRepository,
    private val solutionRepository: SolutionRepository
) : SubjectRepository {
    override suspend fun getAllSubjects(): List<Subject> {
        return subjectDao.getAll().map {
            it.toSubject().copy(
                factors = it.id?.let { id ->
                    factorRepository.getBySubjectId(id)
                } ?: emptyList(),
                solutions = it.id?.let { id ->
                    solutionRepository.getBySubjectId(id)
                } ?: emptyList()
            )
        }
    }

    override suspend fun updateSubject(subject: Subject) {
        subjectDao.addSubject(subject.toEntity())
    }

    override suspend fun deleteSubject(subject: Subject) {
        subjectDao.deleteSubject(subject.toEntity())
    }

    override suspend fun loadById(id: Long): Subject {
        return subjectDao.getById(id).toSubject().copy(
            factors = factorRepository.getBySubjectId(id),
            solutions = solutionRepository.getBySubjectId(id)
        )
    }

    override suspend fun addSubject(subject: Subject): Long {
        return subjectDao.addSubject(subject.toEntity())
    }

}