package com.my.mycoremvvmtesttask.pokemon.data

import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError

interface PokemonCloudDataSource {

    suspend fun requestListOfPokemon(offset: Int, limit: Int): PokemonResponse

    class Base(
        private val pokemonService: PokemonService,
        handleError: HandleError
    ) : CloudDataSource.Abstract(handleError), PokemonCloudDataSource {

        override suspend fun requestListOfPokemon(
            offset: Int,
            limit: Int
        ): PokemonResponse = handle {
            pokemonService.listOfPokemon(offset, limit)
        }
    }
}