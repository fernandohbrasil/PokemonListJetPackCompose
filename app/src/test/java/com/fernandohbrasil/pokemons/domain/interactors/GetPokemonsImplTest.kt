package com.fernandohbrasil.pokemons.domain.interactors

import com.fernandohbrasil.pokemons.RxImmediateSchedulerRule
import com.fernandohbrasil.pokemons.domain.model.PokemonItem
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

class GetPokemonsImplTest {

    private lateinit var sut: GetPokemonsImpl

    @Rule
    @JvmField
    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var pokemonRepository: PokemonRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        sut = GetPokemonsImpl(pokemonRepository)
    }

    @Test
    fun invokeShouldTriggerPokemonRepositoryGetPokemonsAndReturnSuccess() {
        val pokemonItemList = mock<List<PokemonItem>>()
        val success = Single.just(pokemonItemList)

        whenever(pokemonRepository.getPokemons()).thenReturn(success)

        val testObserver = sut().test()
        Mockito.verify(pokemonRepository).getPokemons()
        testObserver.assertValue(pokemonItemList)
    }
}