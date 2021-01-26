package com.chen.morningmvvm.ui.account.login

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chen.moringmvvmlibrary.CoroutinesDispatcherProvider
import com.chen.moringmvvmlibrary.base.BaseViewModel
import com.chen.morningmvvm.model.bean.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

/**
 * 登录页ViewModel
 */
class LoginViewModel(val repository: LoginRepository, val provider: CoroutinesDispatcherProvider) : BaseViewModel() {
    val userName = ObservableField<String>("")
    val passWord = ObservableField<String>("")
    private val _uiState = MutableLiveData<LoginUiState<User>>()
    val uiState: LiveData<LoginUiState<User>>
        get() = _uiState



    private fun isInputValid(userName: String, passWord: String) = userName.isNotBlank() && passWord.isNotBlank()



    fun loginDataChanged() {
        _uiState.value = LoginUiState(enableLoginButton = isInputValid(userName.get()
            ?: "", passWord.get() ?: ""))
    }

    @ExperimentalCoroutinesApi
    fun login() {
        launchOnUI {
            // repo 返回的是一个 flow
            repository.loginFlow(userName.get() ?: "", passWord.get() ?: "")
                .collect {
                    _uiState.postValue(it)
                }
        }
    }

    @ExperimentalCoroutinesApi
    fun register() {
        launchOnUI {
            repository.registerFlow(userName.get() ?: "", passWord.get() ?: "")
                .collect {
                    _uiState.postValue(it)
                }
        }
    }

    val verifyInput: (String) -> Unit = { loginDataChanged() }


}