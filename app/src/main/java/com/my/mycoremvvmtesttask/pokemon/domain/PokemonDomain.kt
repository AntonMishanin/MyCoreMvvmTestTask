package com.my.mycoremvvmtesttask.pokemon.domain

interface PokemonDomain {

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        private val names: List<String>
    ) : PokemonDomain {

        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.map(names)
        }
    }

    interface Mapper<out T : Any> {

        fun map(input: List<String>): T
    }
}