package com.chen.morningmvvm.ui.home

import com.chen.moringmvvmlibrary.base.BaseVMActivity
import com.chen.morningmvvm.R
import com.chen.morningmvvm.databinding.AtyHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * 主页
 */
class HomeAty :BaseVMActivity(){
    private val homeViewModel by viewModel<HomeViewModel>()
    private val binding by binding<AtyHomeBinding>(R.layout.aty_home)

    override fun initView() {
        binding.run {
            viewModel=homeViewModel
        }
    }

    override fun initData() {
    }

    override fun startObserve() {
    }
}