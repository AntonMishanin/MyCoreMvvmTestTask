package com.my.mycoremvvmtesttask.pokemon.domain

interface PokemonRepository {

    suspend fun requestFreshPokemon(offset: Int, limit: Int): PokemonDomain

    suspend fun requestCachedPokemon(): PokemonDomain

    suspend fun deletePokemon(name: String)
}