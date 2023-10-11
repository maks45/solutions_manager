package com.durov.solutions.manager.di

import android.content.Context
import androidx.room.Room
import com.durov.solutions.manager.db.SMDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModules {
    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): SMDatabase {
        return Room.databaseBuilder(
            context,
            SMDatabase::class.java,
            SMDatabase.NAME
        ).build()
    }



}