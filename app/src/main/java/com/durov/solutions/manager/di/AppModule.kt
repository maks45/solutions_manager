package com.durov.solutions.manager.di

import androidx.room.Room
import com.durov.solutions.manager.db.FactorDao
import com.durov.solutions.manager.db.SMDatabase
import com.durov.solutions.manager.db.SolutionDao
import com.durov.solutions.manager.db.SubjectDao
import com.durov.solutions.manager.domain.navigation.NavigationRepository
import com.durov.solutions.manager.domain.navigation.NavigationRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val AppModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            SMDatabase::class.java,
            SMDatabase.NAME
        ).build()
    }

    single<SubjectDao> {
        val database: SMDatabase = get()
        database.getSubjectsDao()
    }

    single<FactorDao> {
        val database: SMDatabase = get()
        database.getFactorDao()
    }

    single<SolutionDao> {
        val database: SMDatabase = get()
        database.getSolutionDao()
    }

    single<NavigationRepository> {
        NavigationRepositoryImpl()
    }

}
