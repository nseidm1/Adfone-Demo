package com.noahseidman.adphone

import android.app.Application
import com.noahseidman.adphone.di.InjectionsUtils

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        InjectionsUtils.init(this)
    }
}