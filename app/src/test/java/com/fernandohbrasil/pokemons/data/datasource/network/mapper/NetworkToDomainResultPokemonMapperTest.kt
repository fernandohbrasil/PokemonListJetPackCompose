package com.fernandohbrasil.pokemons.data.datasource.network.mapper

import com.fernandohbrasil.pokemons.core.extensions.formattedHeight
import com.fernandohbrasil.pokemons.core.extensions.formattedOrder
import com.fernandohbrasil.pokemons.core.extensions.formattedWeight
import com.fernandohbrasil.pokemons.core.extensions.imageUrlFromId
import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkPokemon
import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkType
import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkTypesItem
import com.fernandohbrasil.pokemons.domain.model.Pokemon
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NetworkToDomainResultPokemonMapperTest {

    private companion object {
        const val ID = 0
        const val NAME = "name"
        const val TYPE_NAME = "type_name"
        const val ORDER = 1
        const val URL = "url"
        const val WEIGHT = 1
        const val HEIGHT = 1

        const val EMPTY_STRING = ""
    }

    private lateinit var sut: NetworkToDomainResultPokemonMapper

    @Before
    fun setup() {
        sut = NetworkToDomainResultPokemonMapper()
    }

    @Test
    fun mapShouldMapNetworkPokemonToPokemon() {
        val types = listOf(
            NetworkTypesItem(NetworkType(TYPE_NAME, URL)),
            NetworkTypesItem(NetworkType(TYPE_NAME, URL))
        )
        val origin = NetworkPokemon(ID, NAME, ORDER, types, WEIGHT, HEIGHT)
        val expected = Pokemon(
            url = ID.imageUrlFromId(),
            name = NAME.replaceFirstChar { it.uppercase() },
            order = ORDER.formattedOrder(),
            types = "$TYPE_NAME; $TYPE_NAME",
            weight = WEIGHT.formattedWeight(),
            height = HEIGHT.formattedHeight()
        )

        val result = sut.map(origin)
        assertEquals(expected, result)
    }

    @Test
    fun mapShouldCreatePokemonWithEmptyValuesWhenNetworkPokemonValuesAreNull() {
        val origin = NetworkPokemon(null, null, null, null, null, null)
        val expected = Pokemon(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING)

        val result = sut.map(origin)
        assertEquals(expected, result)
    }
}