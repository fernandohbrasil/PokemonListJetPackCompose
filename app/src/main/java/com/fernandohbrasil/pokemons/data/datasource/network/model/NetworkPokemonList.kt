package com.fernandohbrasil.pokemons.data.datasource.network.model

import androidx.annotation.Keep

@Keep
data class NetworkPokemonList(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<NetworkPokemonItem>?
)