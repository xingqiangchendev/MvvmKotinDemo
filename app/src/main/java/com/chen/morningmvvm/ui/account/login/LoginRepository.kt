package com.chen.morningmvvm.ui.account.login

import com.chen.morningmvvm.model.api.BaseRepository
import com.chen.morningmvvm.model.api.RetrofitService
import com.chen.morningmvvm.utils.network.Preference


/**
 * 登陆页model
 */
class LoginRepository(val service: RetrofitService) : BaseRepository() {

    private var isLogin by Preference(Preference.IS_LOGIN, false)
    private var userJson by Preference(Preference.USER_GSON, "")
}