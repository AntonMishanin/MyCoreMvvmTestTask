package com.my.mycoremvvmtesttask.pokemon

import com.github.johnnysc.coremvvm.presentation.HandleUiError
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
        val errorCommunication = ErrorCommunication()
        val handleUiError = HandleUiError(coreModule, errorCommunication)
        val pokemonInteractor = PokemonInteractor.Base(
            pokemonRepository,
            paginationConfig,
            handleUiError,
            dispatchers
        )

        val deletePokemon = DeletePokemon.Base()
        val refreshPokemon = RefreshPokemon.Base()
        val pokemonUiMapper = BasePokemonUiMapper(refreshPokemon, deletePokemon)
        val errorMapper = ErrorItemUi.BaseMapper(refreshPokemon)
        val progressMapper = ProgressItemUi.BaseMapper()
        val pokemonCommunication = PokemonCommunication.Base()
        return PokemonViewModel(
            refreshPokemon,
            deletePokemon,
            pokemonUiMapper,
            errorMapper,
            progressMapper,
            pokemonInteractor,
            coreModule.provideCanGoBack(),
            dispatchers,
            errorCommunication,
            pokemonCommunication
        )
    }
}