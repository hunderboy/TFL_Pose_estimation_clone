package com.example.posenet_demo

import android.app.Application
import android.content.Context


class MyApplication : Application() {

    init {
        instance = this
    }
    companion object {
        lateinit var instance: MyApplication
        fun getApplicationContext(): Context {
            return instance.applicationContext
        }
    }

}