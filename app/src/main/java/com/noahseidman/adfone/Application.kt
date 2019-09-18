package com.noahseidman.adfone

import android.app.Application
import com.noahseidman.adfone.di.InjectionsUtils

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        InjectionsUtils.init(this)
    }
}