package com.durov.solutions.manager

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SMApp : Application() {

    override fun onCreate() {
        super.onCreate()

    }

}