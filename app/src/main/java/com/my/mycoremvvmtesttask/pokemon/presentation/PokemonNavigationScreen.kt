package com.my.mycoremvvmtesttask.pokemon.presentation

import com.github.johnnysc.coremvvm.presentation.NavigationScreen
import com.github.johnnysc.coremvvm.presentation.ShowStrategy

class PokemonNavigationScreen(
    showStrategy: ShowStrategy = ShowStrategy.ADD
) : NavigationScreen(
    "PokemonNavigationScreen",
    PokemonFragment::class.java,
    showStrategy
)