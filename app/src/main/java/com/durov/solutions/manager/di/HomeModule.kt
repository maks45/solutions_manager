package com.durov.solutions.manager.di

import com.durov.solutions.manager.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val HomeModule = module {

    viewModel {
        HomeViewModel()
    }

}