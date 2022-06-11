package com.my.mycoremvvmtesttask.pokemon.presentation

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

interface PokemonItemUi {

    data class Pokemon(
        private val name: String
    ) : PokemonItemUi, ItemUi {

        override fun content() = name

        override fun id() = name

        override fun show(vararg views: MyView) {
            views[0].show(name)
        }

        override fun type() = 4
    }

    class NoInternetError : PokemonItemUi, ItemUi {

        override fun content() = "NoInternetError"

        override fun id() = "NoInternetError"

        override fun show(vararg views: MyView) = Unit

        override fun type() = 3
    }

    class ServerError : PokemonItemUi, ItemUi {

        override fun content() = "ServerError"

        override fun id() = "ServerError"

        override fun show(vararg views: MyView) = Unit

        override fun type() = 2
    }

    class Progress : PokemonItemUi, ItemUi {

        override fun content() = "Progress"

        override fun id() = "Progress"

        override fun show(vararg views: MyView) = Unit

        override fun type() = 1
    }

    class Empty : PokemonItemUi, ItemUi {

        override fun content() = "Empty"

        override fun id() = "Empty"

        override fun show(vararg views: MyView) = Unit

        override fun type() = 0
    }
}