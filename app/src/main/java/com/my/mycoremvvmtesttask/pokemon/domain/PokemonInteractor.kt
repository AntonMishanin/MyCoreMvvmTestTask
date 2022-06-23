package com.my.mycoremvvmtesttask.pokemon.domain

import com.github.johnnysc.coremvvm.core.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

interface PokemonInteractor {

    fun fetchListOfPokemon(
        coroutineScope: CoroutineScope,
        result: (ResponseState) -> Unit
    ): Job

    fun deletePokemon(
        name: String,
        coroutineScope: CoroutineScope,
        result: (ResponseState) -> Unit
    ): Job

    class Base(
        private val pokemonRepository: PokemonRepository,
        private val paginationConfig: PaginationConfig,
        private val dispatchers: Dispatchers
    ) : PokemonInteractor {

        override fun fetchListOfPokemon(
            coroutineScope: CoroutineScope,
            result: (ResponseState) -> Unit
        ) = dispatchers.launchBackground(coroutineScope) {

            dispatchers.changeToUI { result.invoke(ResponseState.Progress()) }

            val responseState = pokemonRepository.requestFreshPokemon(
                offset = paginationConfig.offset(),
                limit = paginationConfig.limit()
            )

            dispatchers.changeToUI { result.invoke(responseState) }
        }

        override fun deletePokemon(
            name: String,
            coroutineScope: CoroutineScope,
            result: (ResponseState) -> Unit
        ) = dispatchers.launchUI(coroutineScope) {

            pokemonRepository.deletePokemon(name)

            result(pokemonRepository.requestCachedPokemon())
        }
    }
}