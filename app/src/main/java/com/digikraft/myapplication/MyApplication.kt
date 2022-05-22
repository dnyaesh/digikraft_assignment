package com.digikraft.myapplication

import android.app.Application
import com.digikraft.myapplication.di.ApplicationComponent
import com.digikraft.myapplication.di.DaggerApplicationComponent

class MyApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        // Init application component
        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}