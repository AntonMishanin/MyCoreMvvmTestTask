package com.my.mycoremvvmtesttask.pokemon.data

interface PokemonCloudDataSource {

    suspend fun requestListOfPokemon(offset: Int, limit: Int): PokemonResponse

    class Base(
        private val pokemonService: PokemonService
    ) : PokemonCloudDataSource {

        override suspend fun requestListOfPokemon(
            offset: Int,
            limit: Int
        ) = pokemonService.listOfPokemon(offset, limit)
    }
}