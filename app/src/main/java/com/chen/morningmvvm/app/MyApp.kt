package com.chen.morningmvvm.app

import android.app.Application
import android.content.Context
import com.chen.moringmvvmlibrary.utils.Utils
import com.chen.morningmvvm.di.appModule
import com.chen.morningmvvm.model.bean.User
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlin.properties.Delegates

class MyApp: Application() {
    companion object {
        var CONTEXT: Context by Delegates.notNull()
        lateinit var CURRENT_USER: User
    }

    override fun onCreate() {
        super.onCreate()
        //初始化工具类
        CONTEXT = applicationContext
        Utils.init(applicationContext)
        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }

    }



}