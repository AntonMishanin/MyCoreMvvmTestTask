package com.my.mycoremvvmtesttask.pokemon.data

import com.my.mycoremvvmtesttask.pokemon.domain.PaginationConfig
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonDomain
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonRepository

class BasePokemonRepository(
    private val pokemonCloudDataSource: PokemonCloudDataSource,
    private val toDomainMapper: PokemonResponse.Mapper<PokemonDomain>,
    private val cacheDataSource: PokemonCacheDataSource.Mutable
) : PokemonRepository {

    override suspend fun requestFreshPokemon(paginationConfig: PaginationConfig): PokemonDomain {
        val cloud = pokemonCloudDataSource.requestListOfPokemon(paginationConfig)
        cacheDataSource.save(cloud)
        return cloud.map(toDomainMapper)
    }

    override suspend fun requestCachedPokemon() = cacheDataSource.read().map(toDomainMapper)

    override suspend fun deletePokemon(name: String) = cacheDataSource.delete(name)
}