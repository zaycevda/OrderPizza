package com.example.pizzamenu.app

import android.app.Application
import com.example.pizzamenu.app.di.AppModule
import com.example.pizzamenu.app.di.AppModuleImpl

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl()
    }

    companion object {
        lateinit var appModule: AppModule
            private set
    }
}