package com.my.mycoremvvmtesttask.main.presentation

import androidx.fragment.app.FragmentManager
import com.github.johnnysc.coremvvm.presentation.FragmentFactory
import com.github.johnnysc.coremvvm.presentation.NavigationScreen
import com.my.mycoremvvmtesttask.pokemon.presentation.PokemonNavigationScreen

class BaseFragmentFactory(
    containerId: Int,
    fragmentManager: FragmentManager,
) : FragmentFactory.Abstract(
    containerId,
    fragmentManager,
) {

    override val screens: List<NavigationScreen> = listOf(
        PokemonNavigationScreen()
    )
}