package com.chen.morningmvvm.ui.account.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chen.moringmvvmlibrary.CoroutinesDispatcherProvider
import com.chen.moringmvvmlibrary.base.BaseViewModel
import com.chen.moringmvvmlibrary.network.Result
import com.chen.morningmvvm.model.bean.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * 登录页ViewModel
 */
class LoginViewModel(val repository: LoginRepository, val provider: CoroutinesDispatcherProvider) :
    BaseViewModel() {
    val userName = ObservableField<String>("")
    val passWord = ObservableField<String>("")
    private val _uiState = MutableLiveData<LoginUiState<User>>()
    val uiState: LiveData<LoginUiState<User>>
        get() = _uiState


    private fun isInputValid(userName: String, passWord: String) =
        userName.isNotBlank() && passWord.isNotBlank()


    fun loginDataChanged() {
        _uiState.value = LoginUiState(
            enableLoginButton = isInputValid(
                userName.get()
                    ?: "", passWord.get() ?: ""
            )
        )
    }


    fun login() {
        val userName = userName.get() ?: ""
        val passWord = passWord.get() ?: ""
        val map: HashMap<String, String> =
            hashMapOf("username" to userName, "password" to passWord)
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                repository.postResuest("/user/login", map)
            }
            if (result is Result.Success) {
                Log.e("==========cxq", "成功")
                //成功
            } else if (result is Result.Error) {
                //失败
                Log.e("==========cxq", "失败")
            }
        }

    }

    fun register() {
        val userName = userName.get() ?: ""
        val passWord = passWord.get() ?: ""
        val map: HashMap<String, String> =
            hashMapOf("username" to userName, "password" to passWord)
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                Log.e("==========cxq", "postRegister2")
                repository.postResuest("/user/register", map)
            }
            if (result is Result.Success) {
                Log.e("==========cxq", "成功2")
                //成功
            } else if (result is Result.Error) {
                //失败
                Log.e("==========cxq", "失败2:$result")
            }
        }

    }


    val verifyInput: (String) -> Unit = { loginDataChanged() }


}