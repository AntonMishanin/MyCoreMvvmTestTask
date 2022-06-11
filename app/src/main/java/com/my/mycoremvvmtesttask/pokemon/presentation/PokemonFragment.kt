package com.my.mycoremvvmtesttask.pokemon.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.my.mycoremvvmtesttask.R
import com.my.mycoremvvmtesttask.pokemon.presentation.adapter.PokemonAdapter

class PokemonFragment : BackPress.Fragment<List<ItemUi>, PokemonViewModel>() {

    override fun viewModelClass() = PokemonViewModel::class.java

    override val layoutResId: Int = R.layout.fragment_pokemon

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val pokemonAdapter = PokemonAdapter.Factory().provide()
        recyclerView.adapter = pokemonAdapter

        viewModel.observe(owner = this, pokemonAdapter::map)
    }
}