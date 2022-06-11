package com.my.mycoremvvmtesttask.pokemon.presentation

import androidx.lifecycle.viewModelScope
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.*
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonInteractor
import com.my.mycoremvvmtesttask.pokemon.domain.ResponseState
import java.lang.IllegalStateException

class PokemonViewModel(
    private val stateMapperFactory: StateMapperFactory,
    pokemonInteractor: PokemonInteractor,
    canGoBackCallback: CanGoBack.Callback,
    dispatchers: Dispatchers,
    communication: Communication.Mutable<List<ItemUi>>
) : BackPress.ViewModel<List<ItemUi>>(
    canGoBackCallback,
    communication,
    dispatchers
) {

    init {
        pokemonInteractor.fetchListOfPokemon(viewModelScope) { responseState ->
            val mapper = stateMapperFactory.provide(responseState::class.java)
            val stateUi = responseState.map(mapper)
            communication.map(stateUi)
        }
    }

    override fun updateCallbacks() = Unit
}