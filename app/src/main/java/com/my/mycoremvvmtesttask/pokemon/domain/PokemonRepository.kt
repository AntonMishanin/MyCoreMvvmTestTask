package com.my.mycoremvvmtesttask.pokemon.domain

interface PokemonRepository {

    suspend fun fetchListOfPokemon(offset: Int, limit: Int): List<PokemonDomain>
}