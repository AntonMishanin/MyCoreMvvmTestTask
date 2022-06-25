package com.my.mycoremvvmtesttask.pokemon

import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module
import com.my.mycoremvvmtesttask.pokemon.data.DataModule
import com.my.mycoremvvmtesttask.pokemon.domain.PaginationConfig
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonInteractor
import com.my.mycoremvvmtesttask.pokemon.presentation.*

class PokemonModule(
    private val coreModule: CoreModule
) : Module<PokemonViewModel> {

    override fun viewModel(): PokemonViewModel {
        val pokemonRepository = DataModule(coreModule).provideRepository()
        val paginationConfig = PaginationConfig.Base()
        val dispatchers = coreModule.dispatchers()

        val pokemonCommunication = PokemonCommunication.Base()
        val refreshPokemon = RefreshPokemon.Base()

        val handleUiError = HandleUiError(
            pokemonCommunication,
            refreshPokemon,
            com.github.johnnysc.coremvvm.presentation.HandleUiError(
                coreModule,
                coreModule.provideGlobalErrorCommunication()
            )
        )
        val pokemonInteractor = PokemonInteractor.Base(
            pokemonRepository,
            paginationConfig,
            handleUiError,
            dispatchers
        )

        val deletePokemon = DeletePokemon.Base()
        val pokemonUiMapper = BasePokemonUiMapper(refreshPokemon, deletePokemon)
        return PokemonViewModel(
            refreshPokemon,
            deletePokemon,
            pokemonUiMapper,
            pokemonInteractor,
            coreModule.provideCanGoBack(),
            dispatchers,
            pokemonCommunication
        )
    }
}