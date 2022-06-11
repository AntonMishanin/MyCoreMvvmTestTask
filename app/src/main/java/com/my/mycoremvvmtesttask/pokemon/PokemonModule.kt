package com.my.mycoremvvmtesttask.pokemon

import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module
import com.my.mycoremvvmtesttask.pokemon.data.DataModule
import com.my.mycoremvvmtesttask.pokemon.domain.PaginationConfig
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonInteractor
import com.my.mycoremvvmtesttask.pokemon.presentation.PokemonCommunication
import com.my.mycoremvvmtesttask.pokemon.presentation.PokemonViewModel
import com.my.mycoremvvmtesttask.pokemon.presentation.StateMapperFactory

class PokemonModule(
    private val coreModule: CoreModule
) : Module<PokemonViewModel> {

    override fun viewModel(): PokemonViewModel {
        val pokemonRepository = DataModule(coreModule).provideRepository()
        val paginationConfig = PaginationConfig.Base()
        val dispatchers = coreModule.dispatchers()
        val pokemonInteractor =
            PokemonInteractor.Base(pokemonRepository, paginationConfig, dispatchers)
        val pokemonCommunication = PokemonCommunication.Base()
        val stateMapperFactory = StateMapperFactory()

        return PokemonViewModel(
            stateMapperFactory,
            pokemonInteractor,
            coreModule.provideCanGoBack(),
            dispatchers,
            pokemonCommunication
        )
    }
}