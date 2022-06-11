package com.my.mycoremvvmtesttask.pokemon.presentation.adapter

import android.view.ViewGroup
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain
import com.my.mycoremvvmtesttask.pokemon.presentation.adapter.ServerErrorViewHolder as ServerErrorViewHolder

class EmptyViewHolderFactoryChain(
    viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : AbstractViewHolderFactoryChain(viewHolderFactoryChain, viewType = 0, ::EmptyViewHolder)

class NoInternetErrorViewHolderFactoryChain(
    viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : AbstractViewHolderFactoryChain(
    viewHolderFactoryChain,
    viewType = 3,
    ::NoInternetErrorViewHolder
)

class PokemonViewHolderFactoryChain(
    viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : AbstractViewHolderFactoryChain(viewHolderFactoryChain, viewType = 4, ::PokemonViewHolder)

class ProgressViewHolderFactoryChain(
    viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : AbstractViewHolderFactoryChain(viewHolderFactoryChain, viewType = 1, ::ProgressViewHolder)

class ServerErrorViewHolderFactoryChain(
    viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : AbstractViewHolderFactoryChain(viewHolderFactoryChain, viewType = 2, ::ServerErrorViewHolder)

abstract class AbstractViewHolderFactoryChain(
    private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>,
    private val viewType: Int,
    private val viewHolder: (ViewGroup) -> AbstractViewHolder
) : ViewHolderFactoryChain<ItemUi> {

    override fun viewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<ItemUi> =
        if (viewType == this.viewType) viewHolder.invoke(parent)
        else viewHolderFactoryChain.viewHolder(parent, viewType)
}