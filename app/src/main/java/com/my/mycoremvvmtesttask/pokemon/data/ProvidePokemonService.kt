package com.my.mycoremvvmtesttask.pokemon.data

import com.github.johnnysc.coremvvm.data.MakeService
import com.github.johnnysc.coremvvm.data.ProvideRetrofitBuilder

interface ProvidePokemonService {

    fun provide(): PokemonService

    class Base(
        retrofitBuilder: ProvideRetrofitBuilder
    ) : MakeService.Abstract(retrofitBuilder), ProvidePokemonService {

        override fun baseUrl() = "https://pokeapi.co/api/v2/"

        override fun provide(): PokemonService = service(PokemonService::class.java)
    }
}