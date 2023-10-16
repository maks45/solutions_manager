package com.durov.solutions.manager.di

import com.durov.solutions.manager.domain.factor.FactorRepository
import com.durov.solutions.manager.domain.factor.FactorRepositoryImpl
import com.durov.solutions.manager.screen.factor.FactorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val FactorModule = module {

    factory<FactorRepository> {
        FactorRepositoryImpl(
            factorDao = get()
        )
    }

    viewModel {
        FactorViewModel(
            factorRepository = get()
        )
    }
}