package com.my.mycoremvvmtesttask.pokemon.presentation

import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.*
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonInteractor
import com.my.mycoremvvmtesttask.pokemon.domain.ResponseState

class PokemonViewModel(
    refreshPokemon: Observe<Unit>,
    deletePokemon: Observe<String>,
    pokemonUiMapper: BasePokemonUiMapper,
    pokemonInteractor: PokemonInteractor,
    canGoBackCallback: CanGoBack.Callback,
    dispatchers: Dispatchers,
    communication: Communication.Mutable<List<ItemUi>>
) : BackPress.ViewModel<List<ItemUi>>(
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

    private val result: suspend (ResponseState) -> Unit = { responseState ->
        val uiState = responseState.map(pokemonUiMapper)
        communication.map(uiState)
    }

    init {
        canGoBack = false

        refreshPokemon.observe {
            communication.map(listOf(ProgressItemUi()))
            handle { pokemonInteractor.fetchListOfPokemon(atFinish, result) }
        }

        deletePokemon.observe { name ->
            handle { pokemonInteractor.deletePokemon(name, result = result) }
        }
    }

    override fun updateCallbacks() =
        canGoBackCallback.updateCallback(canGoBackCallbackInner)
}