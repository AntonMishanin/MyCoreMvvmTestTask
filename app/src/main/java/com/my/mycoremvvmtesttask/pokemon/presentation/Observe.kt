package com.my.mycoremvvmtesttask.pokemon.presentation

interface Observe<out T : Any> {

    fun observe(communication: (T) -> Unit)
}