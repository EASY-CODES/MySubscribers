package com.example.mysubscribers

import android.app.Application
import com.example.mysubscribers.di.databaseModule
import com.example.mysubscribers.di.subscribeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(databaseModule, subscribeModule)
        }
    }
}