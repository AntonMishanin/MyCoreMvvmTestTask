package com.my.mycoremvvmtesttask.pokemon.data

import com.github.johnnysc.coremvvm.data.HandleError
import com.my.mycoremvvmtesttask.pokemon.domain.ResponseState
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonRepository

class BasePokemonRepository(
    private val pokemonCloudDataSource: PokemonCloudDataSource,
    private val toDomainMapper: PokemonResponse.Mapper<ResponseState>,
    handleError: HandleError,
    private val cacheDataSource: PokemonCacheDataSource.Mutable
) : Repository.Abstract(handleError), PokemonRepository {

    override suspend fun requestFreshPokemon(offset: Int, limit: Int) = handle {
        val cloud = pokemonCloudDataSource.requestListOfPokemon(offset, limit)
        cacheDataSource.save(cloud)
        cloud.map(toDomainMapper)
    }

    override suspend fun requestCachedPokemon() = cacheDataSource.read().map(toDomainMapper)

    override suspend fun deleteByName(name: String) = cacheDataSource.deleteByName(name)
}