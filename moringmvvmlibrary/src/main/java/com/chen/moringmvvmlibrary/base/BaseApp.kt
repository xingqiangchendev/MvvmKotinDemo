package com.chen.moringmvvmlibrary.base

import android.app.Application
import android.content.Context
import java.util.*
import kotlin.properties.Delegates

open class BaseApp :Application(){
    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext


    }
}