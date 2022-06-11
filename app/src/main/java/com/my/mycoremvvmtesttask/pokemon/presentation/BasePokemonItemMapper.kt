package com.my.mycoremvvmtesttask.pokemon.presentation

import com.github.johnnysc.coremvvm.domain.NoInternetConnectionException
import com.github.johnnysc.coremvvm.domain.ServiceUnavailableException
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonDomain
import com.my.mycoremvvmtesttask.pokemon.domain.ResponseState
import java.lang.IllegalStateException

class SuccessStateMapper : ResponseState.Mapper<PokemonDomain, List<ItemUi>> {

    override fun map(input: PokemonDomain): List<ItemUi> {
        val pokemonDomainMapper = PokemonDomain.Mapper.ToList()
        val list = input.map(pokemonDomainMapper)

        return when (list.isEmpty()) {
            true -> listOf(PokemonItemUi.Empty())
            false -> list.map { PokemonItemUi.Pokemon(it) }
        }
    }
}

class ErrorStateMapper : ResponseState.Mapper<Exception, List<ItemUi>> {

    override fun map(input: Exception): List<ItemUi> {
        return when (input) {
            is NoInternetConnectionException -> listOf(PokemonItemUi.NoInternetError())
            is ServiceUnavailableException -> listOf(PokemonItemUi.ServerError())
            else -> throw IllegalStateException("Unknown exception")
        }
    }
}

class ProgressStateMapper : ResponseState.Mapper<Any, List<ItemUi>> {

    override fun map(input: Any): List<ItemUi> {
        return listOf(PokemonItemUi.Progress())
    }
}

class StateMapperFactory {

    fun <T : ResponseState> provide(clazz: Class<T>): ResponseState.Mapper<*, List<ItemUi>> =
        when (clazz) {
            ResponseState.Progress::class.java -> ProgressStateMapper()
            ResponseState.Error::class.java -> ErrorStateMapper()
            ResponseState.Success::class.java -> SuccessStateMapper()
            else -> throw IllegalArgumentException()
        }
}