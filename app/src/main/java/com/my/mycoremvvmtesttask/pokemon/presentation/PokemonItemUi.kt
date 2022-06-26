package com.my.mycoremvvmtesttask.pokemon.presentation

import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

data class PokemonItemUi(
    private val name: String,
    private val deletePokemon: DeletePokemon
) : ItemUi {

    override fun content() = id()

    override fun id() = name

    override fun show(vararg views: MyView) {
        views[0].show(name)
        views[1].handleClick {
            deletePokemon.deletePokemon(name)
        }
    }

    override fun type() = 4
}

class ErrorItemUi(
    private val refreshPokemon: RefreshPokemon,
    private val errorMessage: String
) : ItemUi {

    override fun content() = id()

    override fun id() = "ItemError"

    override fun show(vararg views: MyView) {
        views[0].handleClick {
            refreshPokemon.refreshPokemon()
        }
        views[1].show(errorMessage)
    }

    override fun type() = 3

    class BaseMapper(
        private val refreshPokemon: RefreshPokemon
    ) : Mapper<String, List<ItemUi>> {

        override fun map(data: String) = listOf(ErrorItemUi(refreshPokemon, data))
    }
}

class ProgressItemUi : ItemUi {

    override fun content() = id()

    override fun id() = "Progress"

    override fun show(vararg views: MyView) = Unit

    override fun type() = 1

    class BaseMapper : Mapper<Unit, List<ItemUi>> {

        override fun map(data: Unit) = listOf(ProgressItemUi())
    }
}

class EmptyItemUi(
    private val refreshPokemon: RefreshPokemon
) : ItemUi {

    override fun content() = id()

    override fun id() = "Empty"

    override fun show(vararg views: MyView) {
        views[0].handleClick {
            refreshPokemon.refreshPokemon()
        }
    }

    override fun type() = 0
}