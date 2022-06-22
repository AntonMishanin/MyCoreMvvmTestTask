package com.my.mycoremvvmtesttask.pokemon.presentation

import androidx.lifecycle.viewModelScope
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.*
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonInteractor

class PokemonViewModel(
    private val stateMapperFactory: StateMapperFactory,
    private val pokemonInteractor: PokemonInteractor,
    canGoBackCallback: CanGoBack.Callback,
    dispatchers: Dispatchers,
    communication: Communication.Mutable<List<ItemUi>>
) : FetchPokemon, BackPress.ViewModel<List<ItemUi>>(
    canGoBackCallback,
    communication,
    dispatchers
) {

    init {
        fetchPokemon()
    }

    override fun fetchPokemon() {
        pokemonInteractor.fetchListOfPokemon(viewModelScope) { responseState ->
            val mapper = stateMapperFactory.provide(responseState::class.java, fetchPokemon = this)
            val stateUi = responseState.map(mapper)
            communication.map(stateUi)
        }
    }

    override fun updateCallbacks() = Unit
}