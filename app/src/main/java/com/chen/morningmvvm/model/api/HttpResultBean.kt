package com.chen.morningmvvm.model.api

import com.chen.moringmvvmlibrary.network.Result
import com.chen.morningmvvm.model.bean.RetrofitResponse

/**
 * Created by Lu
 * on 2018/4/2 21:52
 */
data class HttpResultBean(val response: Result<Any>,
                          val requestUrl: String,
                          val requestMap: Map<String, String>)