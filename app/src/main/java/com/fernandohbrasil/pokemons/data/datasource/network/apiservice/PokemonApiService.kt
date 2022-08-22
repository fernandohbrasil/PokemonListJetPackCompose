package com.fernandohbrasil.pokemons.data.datasource.network.apiservice

import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkPokemon
import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkPokemonList
import io.reactivex.Single

interface PokemonApiService {

    fun getPokemons(): Single<NetworkPokemonList>

    fun getPokemonByName(name: String): Single<NetworkPokemon>
}
