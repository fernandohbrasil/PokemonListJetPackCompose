package com.fernandohbrasil.pokemons.data.datasource.network.mapper

import com.fernandohbrasil.pokemons.core.extensions.formattedHeight
import com.fernandohbrasil.pokemons.core.extensions.formattedOrder
import com.fernandohbrasil.pokemons.core.extensions.formattedWeight
import com.fernandohbrasil.pokemons.core.extensions.imageUrlFromId
import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkPokemon
import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkTypesItem
import com.fernandohbrasil.pokemons.domain.model.Pokemon

class NetworkToDomainResultPokemonMapper {

    fun map(origin: NetworkPokemon): Pokemon {
        origin.apply {
            return Pokemon(
                url = id?.imageUrlFromId().orEmpty(),
                name = name.orEmpty().replaceFirstChar { it.uppercase() },
                order = order?.formattedOrder().orEmpty(),
                types = getTypes(types),
                weight = weight?.formattedWeight().orEmpty(),
                height = height?.formattedHeight().orEmpty()
            )
        }
    }

    private fun getTypes(types: List<NetworkTypesItem>?): String {
        return types?.map { it.type?.name }?.joinToString("; ").orEmpty()
    }
}