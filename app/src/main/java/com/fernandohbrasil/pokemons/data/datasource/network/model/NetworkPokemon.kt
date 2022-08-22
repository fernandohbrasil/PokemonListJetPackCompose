package com.fernandohbrasil.pokemons.data.datasource.network.model

import androidx.annotation.Keep

@Keep
data class NetworkPokemon(
    val id: Int?,
    val name: String?,
    val order: Int?,
    val types: List<NetworkTypesItem>?,
    val weight: Int?,
    val height: Int?
)