package com.fernandohbrasil.pokemons.domain.interactors

import com.fernandohbrasil.pokemons.RxImmediateSchedulerRule
import com.fernandohbrasil.pokemons.domain.model.Pokemon
import com.fernandohbrasil.pokemons.domain.repository.PokemonRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetPokemonByNameImplTest {

    private companion object {
        const val POKEMON_NAME = "pokemon_name"
    }

    private lateinit var sut: GetPokemonByNameImpl

    @Rule
    @JvmField
    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var pokemonRepository: PokemonRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        sut = GetPokemonByNameImpl(pokemonRepository)
    }

    @Test
    fun invokeShouldTriggerPokemonRepositoryGetPokemonByNameWithProvidedValuesAndReturnSuccess() {
        val pokemon = mock<Pokemon>()
        val success = Single.just(pokemon)

        whenever(pokemonRepository.getPokemonByName(POKEMON_NAME)).thenReturn(success)

        val testObserver = sut(POKEMON_NAME).test()
        Mockito.verify(pokemonRepository).getPokemonByName(POKEMON_NAME)
        testObserver.assertValue(pokemon)
    }
}