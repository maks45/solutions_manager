package com.durov.solutions.manager

import android.app.Application
import com.durov.solutions.manager.di.AppModule
import com.durov.solutions.manager.di.HomeModule
import com.durov.solutions.manager.di.MainModule
import com.durov.solutions.manager.di.SubjectModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class SMApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKoin()

    }

    private fun initKoin() {
        startKoin {
            androidContext(this@SMApp)
            modules(
                listOf(
                    AppModule,
                    MainModule,
                    HomeModule,
                    SubjectModule,
                )
            )
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

}