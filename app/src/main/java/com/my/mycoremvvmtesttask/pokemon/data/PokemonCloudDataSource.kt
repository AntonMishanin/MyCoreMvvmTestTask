package com.my.mycoremvvmtesttask.pokemon.data

import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError
import com.my.mycoremvvmtesttask.pokemon.domain.PaginationConfig

interface PokemonCloudDataSource {

    suspend fun requestListOfPokemon(paginationConfig: PaginationConfig): PokemonResponse

    class Base(
        private val pokemonService: PokemonService,
        handleError: HandleError
    ) : CloudDataSource.Abstract(handleError), PokemonCloudDataSource {

        override suspend fun requestListOfPokemon(
            paginationConfig: PaginationConfig
        ) = handle {
            pokemonService.listOfPokemon(paginationConfig.offset(), paginationConfig.limit())
        }
    }
}