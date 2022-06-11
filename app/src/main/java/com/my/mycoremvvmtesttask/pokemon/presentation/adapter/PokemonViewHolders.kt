package com.my.mycoremvvmtesttask.pokemon.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyTextView
import com.my.mycoremvvmtesttask.R

class EmptyViewHolder(parent: ViewGroup) : AbstractViewHolder(parent, R.layout.item_empty)

class PokemonViewHolder(parent: ViewGroup) : AbstractViewHolder(parent, R.layout.item_pokemon) {

    override fun bind(item: ItemUi) {
        item.show(itemView.findViewById<MyTextView>(R.id.name))
    }
}

class NoInternetErrorViewHolder(
    parent: ViewGroup
) : AbstractViewHolder(parent, R.layout.item_no_internet_error)

class ProgressViewHolder(
    parent: ViewGroup
) : AbstractViewHolder(parent, R.layout.item_progress)

class ServerErrorViewHolder(
    parent: ViewGroup
) : AbstractViewHolder(parent, R.layout.item_server_error)

abstract class AbstractViewHolder(
    parent: ViewGroup,
    @LayoutRes layoutId: Int
) : GenericViewHolder<ItemUi>(
    LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
) {

    override fun bind(item: ItemUi) = Unit
}