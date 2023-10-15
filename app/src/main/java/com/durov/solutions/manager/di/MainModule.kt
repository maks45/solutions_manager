package com.durov.solutions.manager.di

import com.durov.solutions.manager.domain.navigation.NavigationRepository
import com.durov.solutions.manager.domain.navigation.NavigationRepositoryImpl
import com.durov.solutions.manager.screen.error.ExceptionHandler
import com.durov.solutions.manager.screen.error.ExceptionHandlerImpl
import com.durov.solutions.manager.ui.MainViewModel
import com.durov.solutions.manager.ui.NavigationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val MainModule = module {

    single<NavigationRepository> {
        NavigationRepositoryImpl()
    }

    single<ExceptionHandler> {
        ExceptionHandlerImpl()
    }

    viewModel {
        MainViewModel()
    }

    viewModel {
        NavigationViewModel()
    }

}
