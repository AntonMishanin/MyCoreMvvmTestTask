package com.my.mycoremvvmtesttask.pokemon.domain

interface PokemonRepository {

    suspend fun requestListOfPokemon(offset: Int, limit: Int): ResponseState
}