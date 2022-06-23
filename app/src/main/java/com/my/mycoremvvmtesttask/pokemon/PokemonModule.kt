package com.my.mycoremvvmtesttask.pokemon

import com.github.johnnysc.coremvvm.domain.HandleDomainError
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module
import com.my.mycoremvvmtesttask.pokemon.data.DataModule
import com.my.mycoremvvmtesttask.pokemon.presentation.DeletePokemon
import com.my.mycoremvvmtesttask.pokemon.domain.PaginationConfig
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonDomain
import com.my.mycoremvvmtesttask.pokemon.domain.PokemonInteractor
import com.my.mycoremvvmtesttask.pokemon.presentation.RefreshPokemon
import com.my.mycoremvvmtesttask.pokemon.presentation.BasePokemonUiMapper
import com.my.mycoremvvmtesttask.pokemon.presentation.PokemonCommunication
import com.my.mycoremvvmtesttask.pokemon.presentation.PokemonViewModel

class PokemonModule(
    private val coreModule: CoreModule
) : Module<PokemonViewModel> {

    override fun viewModel(): PokemonViewModel {
        val pokemonRepository = DataModule(coreModule).provideRepository()
        val paginationConfig = PaginationConfig.Base()
        val dispatchers = coreModule.dispatchers()
        val handleDomainError = HandleDomainError()
        val pokemonInteractor = PokemonInteractor.Base(
            pokemonRepository,
            paginationConfig,
            handleDomainError,
            dispatchers
        )
        val pokemonCommunication = PokemonCommunication.Base()
        val refreshPokemon = RefreshPokemon.Base()
        val deletePokemon = DeletePokemon.Base()
        val pokemonUiMapper =
            BasePokemonUiMapper(refreshPokemon, deletePokemon, PokemonDomain.Mapper.ToList())

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