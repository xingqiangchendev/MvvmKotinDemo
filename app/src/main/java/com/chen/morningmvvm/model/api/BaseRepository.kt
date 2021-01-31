package com.chen.morningmvvm.model.api

import android.util.Log
import com.chen.moringmvvmlibrary.network.Result
import com.chen.moringmvvmlibrary.network.RetrofitClient
import com.chen.morningmvvm.model.bean.RetrofitResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException

/**
 * Created by luyao
 * on 2019/4/10 9:41
 */
open class BaseRepository {

    /**
     * post请求网络
     */
    suspend fun postResuest(url: String, map: Map<String, String>): Result<Any> {
        return safeApiCall(call = {
            executeResponse(
                RetrofitClient.service.postQueryMap(
                    url,
                    map
                )
            )
        })
    }

    /**
     * 异常处理
     */
    suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            // An exception was thrown when calling the API so we're converting this to an IOException
            Result.Error(IOException(e.toString(), e))
        }
    }

    /**
     * response: 请求返回的数据
     */
    suspend fun <T : Any> executeResponse(
        response: RetrofitResponse<T>,
    ): Result<T> {
        val result = coroutineScope {
            if (response.errorCode == -1) {
                Result.Error(IOException(response.errorMsg))
            } else {
                Result.Success(response.data)
            }
        }
        return result
    }


}