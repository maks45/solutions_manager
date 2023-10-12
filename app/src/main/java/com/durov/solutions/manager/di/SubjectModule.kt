package com.durov.solutions.manager.di

import com.durov.solutions.manager.domain.subject.SubjectRepository
import com.durov.solutions.manager.domain.subject.SubjectRepositoryImpl
import com.durov.solutions.manager.screen.subject.SubjectsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val SubjectModule = module {

    factory<SubjectRepository> {
        SubjectRepositoryImpl(
            subjectDao = get()
        )
    }

    viewModel {
        SubjectsViewModel(
            subjectRepository = get()
        )
    }

}
