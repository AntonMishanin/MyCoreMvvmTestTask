package com.my.mycoremvvmtesttask.pokemon.presentation.adapter

import android.view.ViewGroup
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class EmptyViewHolderFactoryChain(
    viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : AbstractViewHolderFactoryChain(viewHolderFactoryChain, viewType = 0, ::EmptyViewHolder)

class ErrorViewHolderFactoryChain(
    viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : AbstractViewHolderFactoryChain(
    viewHolderFactoryChain,
    viewType = 3,
    ::ErrorViewHolder
)

class PokemonViewHolderFactoryChain(
    viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : AbstractViewHolderFactoryChain(viewHolderFactoryChain, viewType = 4, ::PokemonViewHolder)

class ProgressViewHolderFactoryChain(
    viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : AbstractViewHolderFactoryChain(viewHolderFactoryChain, viewType = 1, ::ProgressViewHolder)

abstract class AbstractViewHolderFactoryChain(
    private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>,
    private val viewType: Int,
    private val viewHolder: (ViewGroup) -> AbstractViewHolder
) : ViewHolderFactoryChain<ItemUi> {

    override fun viewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<ItemUi> =
        if (viewType == this.viewType) viewHolder.invoke(parent)
        else viewHolderFactoryChain.viewHolder(parent, viewType)
}