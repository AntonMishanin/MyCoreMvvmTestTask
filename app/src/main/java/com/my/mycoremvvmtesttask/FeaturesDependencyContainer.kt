package com.my.mycoremvvmtesttask

import androidx.lifecycle.ViewModel
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.coremvvm.sl.Module
import com.my.mycoremvvmtesttask.main.MainModule
import com.my.mycoremvvmtesttask.main.presentation.MainViewModel
import com.my.mycoremvvmtesttask.pokemon.PokemonModule
import com.my.mycoremvvmtesttask.pokemon.presentation.PokemonViewModel

class FeaturesDependencyContainer(
    private val coreModule: CoreModule,
    private val dependencyContainer: DependencyContainer
) : DependencyContainer {

    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> = when (clazz) {
        MainViewModel::class.java -> MainModule(coreModule)
        PokemonViewModel::class.java -> PokemonModule(coreModule)
        else -> dependencyContainer.module(clazz)
    }
}