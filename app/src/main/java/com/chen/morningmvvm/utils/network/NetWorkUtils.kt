package com.chen.moringmvvmlibrary.network

import android.content.Context
import android.net.ConnectivityManager

/**
 * 网络工具类
 */
class NetWorkUtils {
    companion object {
        /**
         * 判断网络是否有网
         */
        fun isNetworkAvailable(context: Context): Boolean {
            val manager = context.applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = manager.activeNetworkInfo
            return !(null == info || !info.isAvailable)
        }
    }
}