package com.fernandohbrasil.pokemons.data.datasource.network.mapper

import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkPokemonItem
import com.fernandohbrasil.pokemons.domain.model.PokemonItem

class NetworkToDomainResultPokemonItemMapper {

    fun map(origin: List<NetworkPokemonItem>?): List<PokemonItem> {
        return origin?.map {
            PokemonItem(
                it.name,
                it.url
            )
        }.orEmpty()
    }
}