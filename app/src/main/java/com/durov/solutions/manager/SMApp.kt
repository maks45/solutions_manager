package com.durov.solutions.manager

import android.app.Application
import com.durov.solutions.manager.di.AppModule
import com.durov.solutions.manager.di.SubjectModule
import com.durov.solutions.manager.model.Subject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SMApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SMApp)
            modules(
                listOf(
                    AppModule,
                    SubjectModule
                )
            )
        }
    }

}