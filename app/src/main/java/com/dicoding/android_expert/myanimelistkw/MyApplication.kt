package com.dicoding.android_expert.myanimelistkw

import android.app.Application
import com.dicoding.android_expert.core.di.databaseModule
import com.dicoding.android_expert.core.di.networkModule
import com.dicoding.android_expert.core.di.repositoryModule
import com.dicoding.android_expert.myanimelistkw.di.useCaseModule
import com.dicoding.android_expert.myanimelistkw.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}