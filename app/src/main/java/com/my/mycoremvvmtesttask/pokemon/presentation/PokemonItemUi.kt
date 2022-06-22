package com.my.mycoremvvmtesttask.pokemon.presentation

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

data class PokemonItemUi(
    private val name: String
) : ItemUi {

    override fun content() = id()

    override fun id() = name

    override fun show(vararg views: MyView) {
        views[0].show(name)
    }

    override fun type() = 4
}

class NoInternetErrorItemUi : ItemUi {

    override fun content() = id()

    override fun id() = "NoInternetError"

    override fun show(vararg views: MyView) = Unit

    override fun type() = 3
}

class ServerErrorItemUi : ItemUi {

    override fun content() = id()

    override fun id() = "ServerError"

    override fun show(vararg views: MyView) = Unit

    override fun type() = 2
}

class ProgressItemUi : ItemUi {

    override fun content() = id()

    override fun id() = "Progress"

    override fun show(vararg views: MyView) = Unit

    override fun type() = 1
}

class EmptyItemUi : ItemUi {

    override fun content() = id()

    override fun id() = "Empty"

    override fun show(vararg views: MyView) = Unit

    override fun type() = 0
}