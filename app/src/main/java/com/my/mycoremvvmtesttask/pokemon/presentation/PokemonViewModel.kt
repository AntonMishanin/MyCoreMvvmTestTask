package com.my.mycoremvvmtesttask.pokemon.presentation

import androidx.lifecycle.viewModelScope
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.*
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonInteractor
import com.my.mycoremvvmtesttask.pokemon.domain.ResponseState

class PokemonViewModel(
    private val stateMapperFactory: StateMapperFactory,
    private val pokemonInteractor: PokemonInteractor,
    canGoBackCallback: CanGoBack.Callback,
    dispatchers: Dispatchers,
    communication: Communication.Mutable<List<ItemUi>>
) : RefreshPokemon, DeletePokemon, BackPress.ViewModel<List<ItemUi>>(
    canGoBackCallback,
    communication,
    dispatchers
) {

    init {
        refreshPokemon()
    }

    override fun refreshPokemon() {
        pokemonInteractor.fetchListOfPokemon(viewModelScope, ::handleResponseState)
    }

    override fun deletePokemon(name: String) {
        pokemonInteractor.deletePokemon(name, viewModelScope, ::handleResponseState)
    }

    private fun handleResponseState(responseState: ResponseState) {
        val mapper = stateMapperFactory.provide(
            responseState::class.java,
            refreshPokemon = this,
            deletePokemon = this
        )
        val stateUi = responseState.map(mapper)
        communication.map(stateUi)
    }

    override fun updateCallbacks() = Unit
}