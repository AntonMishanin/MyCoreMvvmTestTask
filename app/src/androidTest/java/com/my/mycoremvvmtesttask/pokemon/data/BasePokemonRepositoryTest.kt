package com.my.mycoremvvmtesttask.pokemon.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.johnnysc.coremvvm.sl.CoreModule
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BasePokemonRepositoryTest : TestCase() {

    @Test
    fun should_return_list_of_pokemon_domain() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val coreModule = CoreModule.Base(appContext)
        val pokemonRepository = DataModule(coreModule).provideRepository()

        runTest {
            val list = pokemonRepository.fetchListOfPokemon(0, 20)

            val expected = 20
            val actual = list.size
            assertEquals(expected, actual)
        }
    }
}