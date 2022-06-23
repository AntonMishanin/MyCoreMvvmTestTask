package com.my.mycoremvvmtesttask.pokemon.data

import com.github.johnnysc.coremvvm.core.Read
import com.github.johnnysc.coremvvm.core.Save

interface PokemonCacheDataSource {

    interface Mutable : Save<PokemonResponse>, Read<PokemonResponse>, Delete

    class Base(
        private var data: PokemonResponse = PokemonResponse.Empty()
    ) : Mutable {

        override fun save(data: PokemonResponse) {
            this.data = data
        }

        override fun read() = data

        override fun delete(name: String) {
            val results = data.deletePokemonIfFind(name)
            val newData = data.copy(results = results)
            save(newData)
        }
    }
}

interface Delete {

    fun delete(name: String)
}