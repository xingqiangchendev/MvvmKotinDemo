package com.chen.morningmvvm.model.api

import com.alibaba.fastjson.JSON
import com.chen.morningmvvm.model.bean.RetrofitResponse
import com.chen.morningmvvm.model.bean.User
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.http.*
import java.util.*

/**
 * Retrofit
 */
interface RetrofitService {
    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

//    @FormUrlEncoded
//    @POST("/user/login")
//    suspend fun login(
//        @Field("username") userName: String,
//        @Field("password") passWord: String
//    ): RetrofitResponse<User>
//
//    @FormUrlEncoded
//    @POST("/user/register")
//    suspend fun register(
//        @Field("username") userName: String,
//        @Field("password") passWord: String,
//        @Field("repassword") rePassWord: String
//    ): RetrofitResponse<User>

    @FormUrlEncoded
    @POST
    suspend fun postQueryMap(
        @Url url: String,
        @FieldMap map: Map<String, String>
    ): RetrofitResponse<Any>


    @GET
    suspend fun getQueryMap(
        @Url url: String,
        @QueryMap map: Map<String, Any>
    ): RetrofitResponse<Any>


}