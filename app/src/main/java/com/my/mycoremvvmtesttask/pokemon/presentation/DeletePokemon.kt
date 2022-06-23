package com.my.mycoremvvmtesttask.pokemon.presentation

interface DeletePokemon {

    fun deletePokemon(name: String)

    class Base(
        private var mutableCommunication: (String) -> Unit = {}
    ) : DeletePokemon, Observe<String> {

        override fun observe(communication: (String) -> Unit) {
            mutableCommunication = communication
        }

        override fun deletePokemon(name: String) {
            mutableCommunication.invoke(name)
        }
    }
}