package com.fernandohbrasil.pokemons.domain.repository

import com.fernandohbrasil.pokemons.domain.model.Pokemon
import com.fernandohbrasil.pokemons.domain.model.PokemonItem
import io.reactivex.Single

interface PokemonRepository {

    fun getPokemons(): Single<List<PokemonItem>>

    fun getPokemonByName(name: String): Single<Pokemon>
}
