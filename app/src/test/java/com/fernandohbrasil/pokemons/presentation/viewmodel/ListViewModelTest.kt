package com.fernandohbrasil.pokemons.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fernandohbrasil.pokemons.RxImmediateSchedulerRule
import com.fernandohbrasil.pokemons.core.extensions.test
import com.fernandohbrasil.pokemons.core.testutils.LiveDataTestObserver
import com.fernandohbrasil.pokemons.domain.interactors.GetPokemons
import com.fernandohbrasil.pokemons.domain.model.PokemonItem
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ListViewModelTest {

    private companion object {
        const val ERROR_MESSAGE = "error"
    }

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()

    private lateinit var sut: ListViewModel

    @Mock
    private lateinit var getPokemons: GetPokemons

    private lateinit var isLoadingObserver: LiveDataTestObserver<Boolean>
    private lateinit var errorObserver: LiveDataTestObserver<String>
    private lateinit var pokemonsLiveDataObserver: LiveDataTestObserver<List<PokemonItem>>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        sut = ListViewModel(getPokemons)

        isLoadingObserver = sut.isLoading.test()
        errorObserver = sut.error.test()
        pokemonsLiveDataObserver = sut.pokemonsLiveData.test()
    }

    @Test
    fun getPokemons_should_returnSuccessWhenGetPokemonsReturnSuccess() {
        val expected = mock<List<PokemonItem>>()
        whenever(getPokemons()).thenReturn(Single.just(expected))

        sut.loadPokemons()

        Mockito.verify(getPokemons).invoke()
        isLoadingObserver.assertValues(false, true, false)
        pokemonsLiveDataObserver.assertValues(expected)
    }

    @Test
    fun getPokemons_should_emitError_when_getPokemonsFails() {
        whenever(getPokemons()).thenReturn(Single.error(Throwable(ERROR_MESSAGE)))

        sut.loadPokemons()

        Mockito.verify(getPokemons).invoke()
        isLoadingObserver.assertValues(false, true, false)
        errorObserver.assertValues(ERROR_MESSAGE)
    }
}