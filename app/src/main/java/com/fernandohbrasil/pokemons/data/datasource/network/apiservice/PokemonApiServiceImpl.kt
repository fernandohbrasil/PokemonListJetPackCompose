package com.fernandohbrasil.pokemons.data.datasource.network.apiservice

import com.fernandohbrasil.pokemons.data.datasource.network.api.PokemonApi
import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkPokemon
import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkPokemonList
import io.reactivex.Single

class PokemonApiServiceImpl(
    private val pokemonApi: PokemonApi
) : PokemonApiService {

    override fun getPokemons(): Single<NetworkPokemonList> {
        return pokemonApi.getPokemons()
    }

    override fun getPokemonByName(name: String): Single<NetworkPokemon> {
        return pokemonApi.getPokemonByName(name)
    }
}
