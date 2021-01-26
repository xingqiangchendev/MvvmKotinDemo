package com.chen.morningmvvm.ui.account.login

import com.chen.moringmvvmlibrary.base.BaseViewModel

class LoginUiState<T>(
    isLoading: Boolean = false,
    isSuccess: T? = null,
    isError: String? = null,
    val enableLoginButton: Boolean = false,
    val needLogin: Boolean = false
) : BaseViewModel.UiState<T>(isLoading, false, isSuccess, isError)
