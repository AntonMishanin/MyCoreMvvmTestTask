package com.my.mycoremvvmtesttask.pokemon.domain

import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.Interactor

interface PokemonInteractor {

    suspend fun fetchListOfPokemon(
        atFinish: suspend () -> Unit,
        result: suspend (PokemonDomain) -> Unit
    )

    suspend fun deletePokemon(
        name: String,
        atFinish: suspend () -> Unit = {},
        result: suspend (PokemonDomain) -> Unit
    )

    class Base(
        private val pokemonRepository: PokemonRepository,
        private val paginationConfig: PaginationConfig,
        handleError: HandleError,
        dispatchers: Dispatchers
    ) : Interactor.Abstract(dispatchers, handleError), PokemonInteractor {

        override suspend fun fetchListOfPokemon(
            atFinish: suspend () -> Unit,
            result: suspend (PokemonDomain) -> Unit
        ) = handle(result, atFinish) {
            pokemonRepository.requestFreshPokemon(paginationConfig)
        }

        override suspend fun deletePokemon(
            name: String,
            atFinish: suspend () -> Unit,
            result: suspend (PokemonDomain) -> Unit
        ) = handle(result, atFinish) {
            pokemonRepository.deletePokemon(name)
            pokemonRepository.requestCachedPokemon()
        }
    }
}