package com.my.mycoremvvmtesttask.pokemon.presentation.adapter

import com.github.johnnysc.coremvvm.presentation.adapter.GenericAdapter
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class PokemonAdapter(
    factoryChain: ViewHolderFactoryChain<ItemUi>
) : GenericAdapter.Base(factoryChain) {

    class Factory {

        fun provide(): PokemonAdapter {
            val exceptionChain = ViewHolderFactoryChain.Exception<ItemUi>()
            val serverErrorChain = ServerErrorViewHolderFactoryChain(exceptionChain)
            val noInternetErrorChain = NoInternetErrorViewHolderFactoryChain(serverErrorChain)
            val emptyChain = EmptyViewHolderFactoryChain(noInternetErrorChain)
            val progressChain = ProgressViewHolderFactoryChain(emptyChain)
            val pokemonChain = PokemonViewHolderFactoryChain(progressChain)
            return PokemonAdapter(pokemonChain)
        }
    }
}