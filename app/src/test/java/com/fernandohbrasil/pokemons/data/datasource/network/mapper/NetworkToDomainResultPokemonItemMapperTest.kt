package com.fernandohbrasil.pokemons.data.datasource.network.mapper

import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkPokemonItem
import com.fernandohbrasil.pokemons.domain.model.PokemonItem
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NetworkToDomainResultPokemonItemMapperTest {

    private companion object {
        const val URL = "url"
        const val NAME = "name"
    }

    private lateinit var sut: NetworkToDomainResultPokemonItemMapper

    @Before
    fun setup() {
        sut = NetworkToDomainResultPokemonItemMapper()
    }

    @Test
    fun mapShouldMapListNetworkPokemonItemToListPokemonItem() {
        val origin = listOf(NetworkPokemonItem(NAME, URL))
        val expected = listOf(PokemonItem(NAME, URL))

        val result = sut.map(origin)

        assertEquals(expected, result)
    }

    @Test
    fun mapShouldCreateEmptyListWhenNetworkPokemonItemListIsNull() {
        val origin: List<NetworkPokemonItem>? = null
        val expected = emptyList<PokemonItem>()

        val result = sut.map(origin)

        assertEquals(expected, result)
    }
}