package com.fernandohbrasil.pokemons.data.datasource.network.apiservice

import com.fernandohbrasil.pokemons.RxImmediateSchedulerRule
import com.fernandohbrasil.pokemons.data.datasource.network.api.PokemonApi
import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkPokemon
import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkPokemonList
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.mockito.MockitoAnnotations

class PokemonApiServiceImplTest {

    private companion object {
        const val POKEMON_NAME = "pokemon_name"
    }

    private lateinit var sut: PokemonApiServiceImpl

    @Rule
    @JvmField
    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var pokemonApi: PokemonApi

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        sut = PokemonApiServiceImpl(
            pokemonApi
        )
    }

    @Test
    fun getPokemonsShouldTriggerPokemonApiGetPokemonsAndReturnSuccess() {
        val networkPokemonList = mock<NetworkPokemonList>()
        val success = Single.just(networkPokemonList)
        whenever(pokemonApi.getPokemons()).thenReturn(success)

        val testObserver = sut.getPokemons().test()
        Mockito.verify(pokemonApi).getPokemons()
        testObserver.assertValue(networkPokemonList)
    }

    @Test
    fun getPokemonByNameShouldTriggerPokemonApiGetPokemonByNameWithProvidedValuesAndReturnSuccess() {
        val networkPokemon = mock<NetworkPokemon>()
        val success = Single.just(networkPokemon)

        whenever(pokemonApi.getPokemonByName(POKEMON_NAME)).thenReturn(success)

        val testObserver = sut.getPokemonByName(POKEMON_NAME).test()
        Mockito.verify(pokemonApi).getPokemonByName(POKEMON_NAME)
        testObserver.assertValue(networkPokemon)
    }
}