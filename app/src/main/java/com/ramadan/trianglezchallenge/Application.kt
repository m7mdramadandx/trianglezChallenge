package com.ramadan.trianglezchallenge

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class Application : Application() {


    companion object {
        private var instance: Application? = null

        val appContext: Context
            get() = instance!!.applicationContext

    }

    override fun onCreate() {
        super.onCreate()
    }

}