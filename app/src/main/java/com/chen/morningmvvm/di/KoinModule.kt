package com.chen.morningmvvm.di


import com.chen.moringmvvmlibrary.CoroutinesDispatcherProvider
import com.chen.moringmvvmlibrary.network.RetrofitClient
import com.chen.morningmvvm.ui.account.login.LoginRepository
import com.chen.morningmvvm.model.api.RetrofitService
import com.chen.morningmvvm.ui.account.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val viewModelModule = module {
    viewModel { LoginViewModel(get(),get()) }
}

val repositoryModule = module {
    single { RetrofitClient.getService(RetrofitService::class.java, RetrofitService.BASE_URL) }
    single { CoroutinesDispatcherProvider() }
    single { LoginRepository(get()) }

}

val appModule = listOf(viewModelModule, repositoryModule)