package com.my.mycoremvvmtesttask.pokemon.presentation

interface RefreshPokemon {

    fun refreshPokemon()

    class Base(
        private var mutableCommunication: (Unit) -> Unit = {}
    ) : RefreshPokemon, Observe<Unit> {

        override fun observe(communication: (Unit) -> Unit) {
            mutableCommunication = communication
            mutableCommunication.invoke(Unit)
        }

        override fun refreshPokemon() {
            mutableCommunication.invoke(Unit)
        }
    }
}