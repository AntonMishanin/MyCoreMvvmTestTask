package com.my.mycoremvvmtesttask.pokemon.data

import com.google.gson.annotations.SerializedName
import com.my.mycoremvvmtesttask.pokemon.domain.ResponseState
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonDomain

interface PokemonResponse {

    fun <T : Any> map(mapper: Mapper<T>): T

    fun removeByNameIfFind(name: String): List<PokemonResult.Base>

    fun copy(results: List<PokemonResult.Base>): PokemonResponse

    data class Base(
        @SerializedName("count")
        private val count: Int,
        @SerializedName("next")
        private val next: String?,
        @SerializedName("previous")
        private val previous: String?,
        @SerializedName("results")
        private val results: List<PokemonResult.Base>
    ) : PokemonResponse {

        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.map(count, next, previous, results)
        }

        override fun removeByNameIfFind(name: String): List<PokemonResult.Base> {
            for (i in results.indices) {
                if (results[i].equalsByName(name)) {

                    val mutableResults = results.toMutableList()
                    mutableResults.removeAt(i)
                    return mutableResults
                }
            }
            return results
        }

        override fun copy(results: List<PokemonResult.Base>): PokemonResponse {
            return Base(count, next, previous, results)
        }
    }

    class Empty : PokemonResponse {

        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.map(count = 0, next = "", previous = "", emptyList())
        }

        override fun removeByNameIfFind(name: String) = emptyList<PokemonResult.Base>()

        override fun copy(results: List<PokemonResult.Base>) = Empty()
    }

    interface Mapper<out T : Any> {

        fun map(count: Int, next: String?, previous: String?, results: List<PokemonResult>): T

        class ToDomain : Mapper<ResponseState> {

            override fun map(
                count: Int,
                next: String?,
                previous: String?,
                results: List<PokemonResult>
            ): ResponseState {
                val mapper = PokemonResult.Mapper.ToDomain()
                val listOfPokemon = results.map { it.map(mapper) }
                val value = PokemonDomain.Base(listOfPokemon)
                return ResponseState.Success(value)
            }
        }
    }
}

interface PokemonResult {

    fun <T : Any> map(mapper: Mapper<T>): T

    fun equalsByName(name: String): Boolean

    data class Base(
        @SerializedName("name")
        private val name: String,
        @SerializedName("url")
        private val url: String
    ) : PokemonResult {

        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.map(name, url)
        }

        override fun equalsByName(name: String): Boolean {
            return this.name == name
        }
    }

    interface Mapper<out T : Any> {

        fun map(name: String, url: String): T

        class ToDomain : Mapper<String> {

            override fun map(name: String, url: String): String {
                return name
            }
        }
    }
}