package com.example.posenet_demo

import android.app.Application
import android.content.Context


class MyApplication : Application() {

    init{
        instance = this
    }
    companion object {

        private var instance: MyApplication? = null
        fun getApplicationContext() : Context {
            return instance!!.applicationContext
        }
    }

}