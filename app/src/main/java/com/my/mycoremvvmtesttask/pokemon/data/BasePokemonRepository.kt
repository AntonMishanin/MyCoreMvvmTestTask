package com.my.mycoremvvmtesttask.pokemon.data

import com.github.johnnysc.coremvvm.data.HandleError
import com.my.mycoremvvmtesttask.pokemon.domain.ResponseState
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonRepository

class BasePokemonRepository(
    private val pokemonCloudDataSource: PokemonCloudDataSource,
    private val toDomainMapper: PokemonResponse.Mapper<ResponseState>,
    handleError: HandleError
) : Repository.Abstract(handleError), PokemonRepository {

    override suspend fun requestListOfPokemon(offset: Int, limit: Int) = handle {
        pokemonCloudDataSource.requestListOfPokemon(offset, limit).map(toDomainMapper)
    }
}