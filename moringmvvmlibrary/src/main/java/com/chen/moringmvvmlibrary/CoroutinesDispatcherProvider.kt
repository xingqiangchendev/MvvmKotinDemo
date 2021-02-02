package com.chen.moringmvvmlibrary

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * 线程调度
 */
data class CoroutinesDispatcherProvider  (
    val main: CoroutineDispatcher = Dispatchers.Main,
    val computation: CoroutineDispatcher = Dispatchers.Default,
    val io: CoroutineDispatcher = Dispatchers.IO
) {

    constructor() : this(Dispatchers.Main, Dispatchers.Default, Dispatchers.IO)
}
