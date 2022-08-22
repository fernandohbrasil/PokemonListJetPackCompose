package com.fernandohbrasil.pokemons.domain.interactors

import com.fernandohbrasil.pokemons.domain.model.PokemonItem
import com.fernandohbrasil.pokemons.domain.repository.PokemonRepository
import io.reactivex.Single

class GetPokemonsImpl(private val pokemonRepository: PokemonRepository) : GetPokemons {

    override fun invoke(): Single<List<PokemonItem>> {
        return pokemonRepository.getPokemons()
    }
}