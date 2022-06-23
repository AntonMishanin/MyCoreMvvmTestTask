package com.my.mycoremvvmtesttask.pokemon.domain

interface PokemonRepository {

    suspend fun requestFreshPokemon(offset: Int, limit: Int): ResponseState<*>

    suspend fun requestCachedPokemon(): ResponseState<*>

    suspend fun deletePokemon(name: String)
}