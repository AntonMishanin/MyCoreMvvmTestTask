package com.my.mycoremvvmtesttask.pokemon.presentation

import com.github.johnnysc.coremvvm.presentation.Communication
import com.github.johnnysc.coremvvm.presentation.GlobalErrorCommunication
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface PokemonCommunication : Communication.Mutable<List<ItemUi>> {

    class Base : Communication.UiUpdate<List<ItemUi>>(), PokemonCommunication
}

class ErrorCommunication : Communication.SinglePostUpdate<String>(), GlobalErrorCommunication.Mutable