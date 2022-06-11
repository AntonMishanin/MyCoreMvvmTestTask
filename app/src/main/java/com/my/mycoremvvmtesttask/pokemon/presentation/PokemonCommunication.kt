package com.my.mycoremvvmtesttask.pokemon.presentation

import com.github.johnnysc.coremvvm.presentation.Communication
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface PokemonCommunication : Communication.Mutable<List<ItemUi>> {

    class Base : Communication.UiUpdate<List<ItemUi>>(), PokemonCommunication
}