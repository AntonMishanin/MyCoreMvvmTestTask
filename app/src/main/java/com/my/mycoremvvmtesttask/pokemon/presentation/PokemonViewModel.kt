package com.my.mycoremvvmtesttask.pokemon.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.coremvvm.presentation.*
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonDomain
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonInteractor

class PokemonViewModel(
    refreshPokemon: Observe<Unit>,
    deletePokemon: Observe<String>,
    pokemonUiMapper: PokemonDomain.Mapper<List<ItemUi>>,
    private val errorMapper: Mapper<String, List<ItemUi>>,
    progressMapper: Mapper<Unit, List<ItemUi>>,
    pokemonInteractor: PokemonInteractor,
    canGoBackCallback: CanGoBack.Callback,
    dispatchers: Dispatchers,
    private val errorCommunication: Communication.Mutable<String>,
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

    private val result: suspend (PokemonDomain) -> Unit = { pokemonDomain ->
        val uiState = pokemonDomain.map(pokemonUiMapper)
        communication.map(uiState)
    }

    init {
        canGoBack = false

        refreshPokemon.observe {
            communication.map(progressMapper.map(Unit))
            handle { pokemonInteractor.fetchListOfPokemon(atFinish, result) }
        }

        deletePokemon.observe { name ->
            handle { pokemonInteractor.deletePokemon(name, result = result) }
        }
    }

    override fun updateCallbacks() = canGoBackCallback.updateCallback(canGoBackCallbackInner)

    override fun observe(owner: LifecycleOwner, observer: Observer<List<ItemUi>>) {
        super.observe(owner, observer)
        errorCommunication.observe(owner, { communication.map(errorMapper.map(it)) })
    }
}