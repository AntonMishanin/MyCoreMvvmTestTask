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

        fun map(names: List<String>): T

        class ToList : Mapper<List<String>> {

            override fun map(names: List<String>): List<String> {
                return names
            }
        }
    }
}