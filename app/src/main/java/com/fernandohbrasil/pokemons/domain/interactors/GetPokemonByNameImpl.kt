package com.fernandohbrasil.pokemons.domain.interactors

import com.fernandohbrasil.pokemons.domain.model.Pokemon
import com.fernandohbrasil.pokemons.domain.repository.PokemonRepository
import io.reactivex.Single

class GetPokemonByNameImpl(private val pokemonRepository: PokemonRepository) : GetPokemonByName {

    override fun invoke(name: String): Single<Pokemon> {
        return pokemonRepository.getPokemonByName(name)
    }
}