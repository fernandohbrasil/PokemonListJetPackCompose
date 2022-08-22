package com.fernandohbrasil.pokemons.data.repository

import com.fernandohbrasil.pokemons.data.datasource.network.apiservice.PokemonApiService
import com.fernandohbrasil.pokemons.data.datasource.network.mapper.NetworkToDomainResultPokemonItemMapper
import com.fernandohbrasil.pokemons.data.datasource.network.mapper.NetworkToDomainResultPokemonMapper
import com.fernandohbrasil.pokemons.domain.model.Pokemon
import com.fernandohbrasil.pokemons.domain.model.PokemonItem
import com.fernandohbrasil.pokemons.domain.repository.PokemonRepository
import io.reactivex.Single

class PokemonRepositoryImpl(
    private val pokemonApiService: PokemonApiService,
    private val networkToDomainResultPokemonItemMapper: NetworkToDomainResultPokemonItemMapper,
    private val networkToDomainResultPokemonMapper : NetworkToDomainResultPokemonMapper
) : PokemonRepository {

    override fun getPokemons(): Single<List<PokemonItem>> {
        return pokemonApiService.getPokemons().map { networkToDomainResultPokemonItemMapper.map(it.results) }
    }

    override fun getPokemonByName(name: String): Single<Pokemon> {
        return pokemonApiService.getPokemonByName(name).map { networkToDomainResultPokemonMapper.map(it) }
    }
}