package com.gemidroid.comicshow.base

import android.app.Application
import com.gemidroid.comicshow.di.comicsModule
import com.gemidroid.comicshow.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin
    }

    private val initKoin = startKoin {
        androidLogger(Level.NONE)
        androidContext(this@BaseApp)
        modules(retrofitModule, comicsModule)
    }
}