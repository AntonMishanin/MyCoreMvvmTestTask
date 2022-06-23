package com.my.mycoremvvmtesttask.pokemon.presentation

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

    private var canGoBack = true

    private val atFinish = suspend {
        canGoBack = true
    }

    private val canGoBackCallbackInner = object : CanGoBack {
        override fun canGoBack() = canGoBack
    }

    init {
        canGoBack = false

        refreshPokemon()
    }

    override fun refreshPokemon() {
        communication.map(listOf(ProgressItemUi()))
        handle {
            pokemonInteractor.fetchListOfPokemon(atFinish, ::handleResponseState)
        }
    }

    override fun deletePokemon(name: String) {
        handle {
            pokemonInteractor.deletePokemon(name, result = ::handleResponseState)
        }
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

    override fun updateCallbacks() =
        canGoBackCallback.updateCallback(canGoBackCallbackInner)
}