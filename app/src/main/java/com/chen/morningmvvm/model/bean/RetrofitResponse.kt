package com.chen.morningmvvm.model.bean

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import com.chen.moringmvvmlibrary.Result
import java.io.IOException


data class RetrofitResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T)

//suspend fun <T : Any> RetrofitResponse<T>.executeResponse(successBlock: (suspend CoroutineScope.() -> Unit)? = null,
//                                                     errorBlock: (suspend CoroutineScope.() -> Unit)? = null): Result<T> {
//    return coroutineScope {
//        if (errorCode == -1) {
//            errorBlock?.let { it() }
//            Result.Error(IOException(errorMsg))
//        } else {
//            successBlock?.let { it() }
//            Result.Success(data)
//        }
//    }
//}

//suspend fun <T : Any> RetrofitResponse<T>.doSuccess(successBlock: (suspend CoroutineScope.(T) -> Unit)? = null): RetrofitResponse<T> {
//    return coroutineScope {
//        if (errorCode != -1) successBlock?.invoke(this, this@doSuccess.data)
//        this@doSuccess
//    }
//
//}
//
//suspend fun <T : Any> RetrofitResponse<T>.doError(errorBlock: (suspend CoroutineScope.(String) -> Unit)? = null): RetrofitResponse<T> {
//    return coroutineScope {
//        if (errorCode == -1) errorBlock?.invoke(this, this@doError.errorMsg)
//        this@doError
//    }
//}

