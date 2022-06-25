package com.my.mycoremvvmtesttask.pokemon.presentation

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonDomain

class BasePokemonUiMapper(
    private val refreshPokemon: RefreshPokemon,
    private val deletePokemon: DeletePokemon
) : PokemonDomain.Mapper<List<ItemUi>> {

    override fun map(input: List<String>): List<ItemUi> {
        return when (input.isEmpty()) {
            true -> listOf(EmptyItemUi(refreshPokemon))
            false -> input.map { name -> PokemonItemUi(name, deletePokemon) }
        }
    }
}