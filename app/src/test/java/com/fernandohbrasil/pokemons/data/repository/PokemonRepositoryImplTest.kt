package com.fernandohbrasil.pokemons.data.repository

import com.fernandohbrasil.pokemons.RxImmediateSchedulerRule
import com.fernandohbrasil.pokemons.data.datasource.network.apiservice.PokemonApiService
import com.fernandohbrasil.pokemons.data.datasource.network.mapper.NetworkToDomainResultPokemonItemMapper
import com.fernandohbrasil.pokemons.data.datasource.network.mapper.NetworkToDomainResultPokemonMapper
import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkPokemon
import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkPokemonItem
import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkPokemonList
import com.fernandohbrasil.pokemons.domain.model.Pokemon
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

class PokemonRepositoryImplTest {

    private companion object {
        const val POKEMON_NAME = "pokemon_name"
    }

    private lateinit var sut: PokemonRepositoryImpl

    @Rule
    @JvmField
    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var pokemonApiService: PokemonApiService

    @Mock
    lateinit var networkToDomainResultPokemonItemMapper: NetworkToDomainResultPokemonItemMapper

    @Mock
    lateinit var networkToDomainResultPokemonMapper: NetworkToDomainResultPokemonMapper

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        sut = PokemonRepositoryImpl(pokemonApiService, networkToDomainResultPokemonItemMapper, networkToDomainResultPokemonMapper)
    }

    @Test
    fun getPokemonsShouldTriggerPokemonApiServiceGetPokemonsAndReturnSuccess() {
        val networkPokemonList = mock<NetworkPokemonList>()
        val networkPokemonItemList = mock<List<NetworkPokemonItem>>()

        whenever(networkPokemonList.results).thenReturn(networkPokemonItemList)
        whenever(pokemonApiService.getPokemons()).thenReturn(Single.just(networkPokemonList))

        val mappedResult = mock<List<PokemonItem>>()
        whenever(networkToDomainResultPokemonItemMapper.map(networkPokemonList.results)).thenReturn(mappedResult)

        val testObserver = sut.getPokemons().test()
        Mockito.verify(pokemonApiService).getPokemons()
        testObserver.assertValue(mappedResult)
    }

    @Test
    fun getPokemonByNameShouldTriggerPokemonApiServiceGetPokemonByNameWithProvidedValuesAndReturnSuccess() {
        val networkPokemon = mock<NetworkPokemon>()

        whenever(pokemonApiService.getPokemonByName(POKEMON_NAME)).thenReturn(Single.just(networkPokemon))

        val mappedResult = mock<Pokemon>()
        whenever(networkToDomainResultPokemonMapper.map(networkPokemon)).thenReturn(mappedResult)

        val testObserver = sut.getPokemonByName(POKEMON_NAME).test()
        Mockito.verify(pokemonApiService).getPokemonByName(POKEMON_NAME)
        testObserver.assertValue(mappedResult)
    }
}