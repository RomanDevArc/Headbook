package com.headbook

import android.app.Application
import com.headbook.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ThisApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ThisApplication)
            androidLogger()
            modules(appModule)
        }
    }
}