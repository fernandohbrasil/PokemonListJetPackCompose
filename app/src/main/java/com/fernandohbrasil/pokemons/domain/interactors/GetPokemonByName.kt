package com.fernandohbrasil.pokemons.domain.interactors

import com.fernandohbrasil.pokemons.domain.model.Pokemon
import io.reactivex.Single

interface GetPokemonByName {

    operator fun invoke(name: String): Single<Pokemon>
}