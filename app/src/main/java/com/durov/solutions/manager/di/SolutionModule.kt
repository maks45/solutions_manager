package com.durov.solutions.manager.di

import com.durov.solutions.manager.domain.solution.SolutionRepository
import com.durov.solutions.manager.domain.solution.SolutionRepositoryImpl
import com.durov.solutions.manager.screen.solution.SolutionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val SolutionModule = module {

    factory<SolutionRepository> {
        SolutionRepositoryImpl(
            solutionDao = get()
        )
    }

    viewModel {
        SolutionViewModel(
            solutionRepository = get()
        )
    }

}