package com.durov.solutions.manager.di

import androidx.room.Room
import com.durov.solutions.manager.db.SMDatabase
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

    single {
        val database: SMDatabase = get()
        database.getSubjectsDao()
    }

}
