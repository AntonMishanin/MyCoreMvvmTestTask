package com.my.mycoremvvmtesttask.main

import com.github.johnnysc.coremvvm.presentation.NavigationCommunication
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module
import com.my.mycoremvvmtesttask.main.presentation.MainViewModel

class MainModule(
    private val coreModule: CoreModule
) : Module<MainViewModel> {

    override fun viewModel() = MainViewModel(
        NavigationCommunication.Base(),
        coreModule.provideCanGoBack(),
        coreModule.dispatchers(),
        coreModule.provideGlobalErrorCommunication()
    )
}