package com.fernandohbrasil.pokemons.domain.interactors

import com.fernandohbrasil.pokemons.domain.model.PokemonItem
import io.reactivex.Single

interface GetPokemons {

    operator fun invoke(): Single<List<PokemonItem>>
}