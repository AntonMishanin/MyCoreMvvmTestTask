package com.my.mycoremvvmtesttask.main.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.*
import com.my.mycoremvvmtesttask.pokemon.presentation.PokemonNavigationScreen

class MainViewModel(
    private val navigationCommunication: NavigationCommunication.Mutable,
    canGoBack: CanGoBack,
    dispatchers: Dispatchers,
    communication: GlobalErrorCommunication.Mutable
) : BackPress.Activity.ViewModel<String>(
    canGoBack,
    communication,
    dispatchers
) {

    private val pokemonNavigationScreen = PokemonNavigationScreen()

    init {
        navigationCommunication.map(pokemonNavigationScreen)
    }

    fun observeNavigation(owner: LifecycleOwner, observer: Observer<NavigationScreen>) {
        navigationCommunication.observe(owner, observer)
    }
}