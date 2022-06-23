package com.my.mycoremvvmtesttask.pokemon.presentation

import com.github.johnnysc.coremvvm.domain.NoInternetConnectionException
import com.github.johnnysc.coremvvm.domain.ServiceUnavailableException
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonDomain
import com.my.mycoremvvmtesttask.pokemon.domain.ResponseState
import java.lang.IllegalStateException

class BasePokemonUiMapper(
    private val refreshPokemon: RefreshPokemon,
    private val deletePokemon: DeletePokemon,
    private val pokemonDomainMapper: PokemonDomain.Mapper.ToList
) : ResponseState.Mapper<Any, List<ItemUi>> {

    override fun map(input: Any): List<ItemUi> {
        return when (input) {
            is NoInternetConnectionException -> listOf(NoInternetErrorItemUi(refreshPokemon))
            is ServiceUnavailableException -> listOf(ServerErrorItemUi(refreshPokemon))
            is PokemonDomain -> {
                val list = input.map(pokemonDomainMapper)
                when (list.isEmpty()) {
                    true -> listOf(EmptyItemUi(refreshPokemon))
                    false -> list.map { name -> PokemonItemUi(name, deletePokemon) }
                }
            }
            else -> throw IllegalStateException("Unknown input $input")
        }
    }
}