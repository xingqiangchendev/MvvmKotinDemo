package com.chen.morningmvvm.ui.account.login

import com.chen.morningmvvm.app.MyApp
import com.chen.morningmvvm.model.api.BaseRepository
import com.chen.morningmvvm.model.api.RetrofitService
import com.chen.morningmvvm.model.bean.User
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.flowOn
import com.chen.morningmvvm.model.bean.doError
import com.chen.morningmvvm.model.bean.doSuccess
import com.chen.morningmvvm.utils.network.Preference
import kotlinx.coroutines.flow.catch

/**
 * 登陆页model
 */
class LoginRepository(val service: RetrofitService) : BaseRepository() {

    private var isLogin by Preference(Preference.IS_LOGIN, false)
    private var userJson by Preference(Preference.USER_GSON, "")

    /**
     * 登录
     */
    @ExperimentalCoroutinesApi
    suspend fun loginFlow(userName: String, passWord: String) = flow {

        // 输入不能为空
        if (userName.isBlank() || passWord.isBlank()) {
            emit(LoginUiState(enableLoginButton = false))
            return@flow
        }


        service.login(userName, passWord).doSuccess { user ->
            isLogin = true
            userJson = Gson().toJson(user)
            MyApp.CURRENT_USER = user
            emit(LoginUiState(isSuccess = user, enableLoginButton = true))
        }.doError { errorMsg ->
            emit(LoginUiState<User>(isError = errorMsg, enableLoginButton = true))
        }
    }.onStart {
        emit(LoginUiState(isLoading = true))
    }.flowOn(Dispatchers.IO)
        .catch { emit(LoginUiState(isError = it.message, enableLoginButton = true))
    }


    /**
     * 注册
     */
    @ExperimentalCoroutinesApi
    suspend fun registerFlow(userName: String, passWord: String) = flow<LoginUiState<User>> {

        // 输入不能为空
        if (userName.isBlank() || passWord.isBlank()) {
            emit(LoginUiState(enableLoginButton = false))
            return@flow
        }

        service.register(userName, passWord, passWord).doSuccess {
            emit(LoginUiState(needLogin = true))
        }.doError { errorMsg ->
            emit(LoginUiState(isError = errorMsg, enableLoginButton = true))
        }
    }.onStart {
        emit(LoginUiState(isLoading = true))
    }.flowOn(Dispatchers.IO)
        .catch { emit(LoginUiState(isError = it.message, enableLoginButton = true)) }

}