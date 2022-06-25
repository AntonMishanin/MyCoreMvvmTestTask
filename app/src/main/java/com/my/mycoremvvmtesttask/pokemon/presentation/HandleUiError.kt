package com.my.mycoremvvmtesttask.pokemon.presentation

import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.NoInternetConnectionException
import com.github.johnnysc.coremvvm.domain.ServiceUnavailableException
import com.github.johnnysc.coremvvm.presentation.HandleUiError

class HandleUiError(
    private val communication: PokemonCommunication,
    private val refreshPokemon: RefreshPokemon,
    private val handleUiError: HandleUiError
) : HandleError {

    override fun handle(error: Exception): Exception {

        if (error is NoInternetConnectionException) {
            communication.map(listOf(NoInternetErrorItemUi(refreshPokemon)))
        } else if (error is ServiceUnavailableException) {
            communication.map(listOf(ServerErrorItemUi(refreshPokemon)))
        }

        return handleUiError.handle(error)
    }
}