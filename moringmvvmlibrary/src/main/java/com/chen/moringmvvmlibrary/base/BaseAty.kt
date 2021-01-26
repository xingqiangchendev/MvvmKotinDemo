package com.chen.moringmvvmlibrary.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseAty:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    abstract fun getLayoutResId(): Int
    abstract fun initView()
    abstract fun initData()
}