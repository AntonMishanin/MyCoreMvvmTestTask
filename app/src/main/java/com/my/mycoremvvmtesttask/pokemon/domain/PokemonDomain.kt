package com.my.mycoremvvmtesttask.pokemon.domain

interface PokemonDomain {

    data class Base(
        private val name: String
    ): PokemonDomain
}