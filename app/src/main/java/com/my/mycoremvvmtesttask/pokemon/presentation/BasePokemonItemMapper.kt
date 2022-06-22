package com.my.mycoremvvmtesttask.pokemon.presentation

import com.github.johnnysc.coremvvm.domain.NoInternetConnectionException
import com.github.johnnysc.coremvvm.domain.ServiceUnavailableException
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonDomain
import com.my.mycoremvvmtesttask.pokemon.domain.ResponseState
import java.lang.IllegalStateException

class SuccessStateMapper(
    private val fetchPokemon: FetchPokemon
) : ResponseState.Mapper<PokemonDomain, List<ItemUi>> {

    override fun map(input: PokemonDomain): List<ItemUi> {
        val pokemonDomainMapper = PokemonDomain.Mapper.ToList()
        val list = input.map(pokemonDomainMapper)

        return when (list.isEmpty()) {
            true -> listOf(EmptyItemUi(fetchPokemon))
            false -> list.map(::PokemonItemUi)
        }
    }
}

class ErrorStateMapper(
    private val fetchPokemon: FetchPokemon
) : ResponseState.Mapper<Exception, List<ItemUi>> {

    override fun map(input: Exception): List<ItemUi> {
        return when (input) {
            is NoInternetConnectionException -> listOf(NoInternetErrorItemUi(fetchPokemon))
            is ServiceUnavailableException -> listOf(ServerErrorItemUi(fetchPokemon))
            else -> throw IllegalStateException("Unknown exception")
        }
    }
}

class ProgressStateMapper : ResponseState.Mapper<Any, List<ItemUi>> {

    override fun map(input: Any): List<ItemUi> {
        return listOf(ProgressItemUi())
    }
}

class StateMapperFactory {

    fun <T : ResponseState> provide(
        clazz: Class<T>,
        fetchPokemon: FetchPokemon
    ): ResponseState.Mapper<*, List<ItemUi>> =
        when (clazz) {
            ResponseState.Progress::class.java -> ProgressStateMapper()
            ResponseState.Error::class.java -> ErrorStateMapper(fetchPokemon)
            ResponseState.Success::class.java -> SuccessStateMapper(fetchPokemon)
            else -> throw IllegalArgumentException()
        }
}