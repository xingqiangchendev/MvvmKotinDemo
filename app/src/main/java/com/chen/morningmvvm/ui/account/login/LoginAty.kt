package com.chen.morningmvvm.ui.account.login

import android.app.ProgressDialog
import androidx.lifecycle.Observer
import com.chen.moringmvvmlibrary.base.BaseVMActivity
import com.chen.morningmvvm.R
import com.chen.morningmvvm.databinding.AtyLoginBinding
import com.chen.morningmvvm.model.bean.Title
import kotlinx.coroutines.ExperimentalCoroutinesApi
import luyao.util.ktx.ext.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 登陆页
 */
class LoginAty : BaseVMActivity() {

    private val loginViewModel by viewModel<LoginViewModel>()
    private val binding by binding<AtyLoginBinding>(R.layout.aty_login)


    override fun initView() {
        binding.run {
            viewModel = loginViewModel
            title = Title(R.string.login, R.drawable.arrow_back) { onBackPressed() }
        }
    }

    override fun initData() {

    }



    @ExperimentalCoroutinesApi
    override fun startObserve() {
        loginViewModel.apply {

            uiState.observe(this@LoginAty, Observer {
                if (it.isLoading) showProgressDialog()

                it.isSuccess?.let {
                    dismissProgressDialog()
                    finish()
                }

                it.isError?.let { err ->
                    dismissProgressDialog()
                    toast(err)
                }

                if (it.needLogin) loginViewModel.login()
            })
        }
    }

    private var progressDialog: ProgressDialog? = null


    private fun showProgressDialog() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(this)
        progressDialog?.show()
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }


}