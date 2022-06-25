package com.my.mycoremvvmtesttask.pokemon.domain

interface PokemonRepository {

    suspend fun requestFreshPokemon(paginationConfig: PaginationConfig): PokemonDomain

    suspend fun requestCachedPokemon(): PokemonDomain

    suspend fun deletePokemon(name: String)
}