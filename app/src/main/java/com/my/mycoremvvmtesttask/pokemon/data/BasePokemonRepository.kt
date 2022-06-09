package com.my.mycoremvvmtesttask.pokemon.data

import com.my.mycoremvvmtesttask.pokemon.domain.PokemonDomain
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonRepository

class BasePokemonRepository(
    private val pokemonCloudDataSource: PokemonCloudDataSource,
    private val toDomainMapper: PokemonResponse.Mapper<List<PokemonDomain>>
) : PokemonRepository {

    override suspend fun fetchListOfPokemon(offset: Int, limit: Int) =
        pokemonCloudDataSource.requestListOfPokemon(offset, limit).map(toDomainMapper)
}