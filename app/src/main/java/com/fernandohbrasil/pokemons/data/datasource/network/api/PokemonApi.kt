package com.fernandohbrasil.pokemons.data.datasource.network.api

import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkPokemon
import com.fernandohbrasil.pokemons.data.datasource.network.model.NetworkPokemonList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon/")
    fun getPokemons(): Single<NetworkPokemonList>

    @GET("pokemon/{name}")
    fun getPokemonByName(@Path("name") name: String): Single<NetworkPokemon>
}