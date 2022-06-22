package com.my.mycoremvvmtesttask.pokemon.data

import com.github.johnnysc.coremvvm.domain.HandleDomainError
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonRepository

class DataModule(
    private val coreModule: CoreModule
) {

    fun provideRepository(): PokemonRepository {
        val pokemonService = ProvidePokemonService.Base(coreModule).provide()
        val pokemonCloudDataSource = PokemonCloudDataSource.Base(pokemonService)
        val toDomainMapper = PokemonResponse.Mapper.ToDomain()
        val handleError = HandleDomainError()
        val cashedDataSource = PokemonCacheDataSource.Base()
        return BasePokemonRepository(
            pokemonCloudDataSource,
            toDomainMapper,
            handleError,
            cashedDataSource
        )
    }
}