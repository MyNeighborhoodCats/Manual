package kr.co.vacatio.ylee.my_neighborhood_cats.manual

import android.app.Application
import android.content.Context
import timber.log.Timber

class Manual : Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance : Manual
        fun applicationContext() : Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}