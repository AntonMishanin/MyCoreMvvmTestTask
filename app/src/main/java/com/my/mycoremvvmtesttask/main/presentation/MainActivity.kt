package com.my.mycoremvvmtesttask.main.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.sl.ProvideViewModel
import com.my.mycoremvvmtesttask.R

class MainActivity : BackPress.Activity<MainViewModel>(), ProvideViewModel {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentFactory = BaseFragmentFactory(R.id.container, supportFragmentManager)

        viewModel = provideViewModel(MainViewModel::class.java, this)

        viewModel.observeNavigation(owner = this, fragmentFactory::fragment)
    }

    override fun <T : androidx.lifecycle.ViewModel> provideViewModel(
        clazz: Class<T>,
        owner: ViewModelStoreOwner
    ): T = (application as ProvideViewModel).provideViewModel(clazz, owner)
}