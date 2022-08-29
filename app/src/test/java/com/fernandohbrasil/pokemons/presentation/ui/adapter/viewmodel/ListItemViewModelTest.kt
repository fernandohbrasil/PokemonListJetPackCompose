package com.fernandohbrasil.pokemons.presentation.ui.adapter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fernandohbrasil.pokemons.RxImmediateSchedulerRule
import com.fernandohbrasil.pokemons.core.extensions.test
import com.fernandohbrasil.pokemons.core.testutils.LiveDataTestObserver
import com.fernandohbrasil.pokemons.domain.interactors.GetPokemonByName
import com.fernandohbrasil.pokemons.domain.model.Pokemon
import com.fernandohbrasil.pokemons.domain.model.PokemonItem
import com.fernandohbrasil.pokemons.presentation.viewmodel.ListItemViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ListItemViewModelTest {

    private companion object {
        const val NAME = "name"
        const val URL = "url"
    }

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()

    private lateinit var sut: ListItemViewModel

    @Mock
    private lateinit var getPokemonByName: GetPokemonByName

    private lateinit var isLoadingObserver: LiveDataTestObserver<Boolean>
    private lateinit var errorObserver: LiveDataTestObserver<Boolean>
    private lateinit var pokemonLiveDataObserver: LiveDataTestObserver<Pokemon>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        sut = ListItemViewModel(getPokemonByName)

        isLoadingObserver = sut.isLoading.test()
        errorObserver = sut.error.test()
        pokemonLiveDataObserver = sut.pokemonLiveData.test()
    }

    @Test
    fun setModel_should_trigger_getPokemonByName_and_returnSuccess() {
        val expected = mock<Pokemon>()
        whenever(getPokemonByName(NAME)).thenReturn(Single.just(expected))

        sut.setModel(PokemonItem(NAME, URL))

        Mockito.verify(getPokemonByName).invoke(NAME)
        isLoadingObserver.assertValues(false, true, false)
        pokemonLiveDataObserver.assertValues(expected)
    }

    @Test
    fun setModel_getPokemons_should_trigger_getPokemonByName_and_emitError() {
        whenever(getPokemonByName(NAME)).thenReturn(Single.error(Throwable()))

        sut.setModel(PokemonItem(NAME, URL))

        Mockito.verify(getPokemonByName).invoke(NAME)
        isLoadingObserver.assertValues(false, true, false)
        errorObserver.assertValues(false, true)
    }
}