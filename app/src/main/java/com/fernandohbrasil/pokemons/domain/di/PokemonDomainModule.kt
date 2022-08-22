package com.fernandohbrasil.pokemons.domain.di

import com.fernandohbrasil.pokemons.domain.interactors.GetPokemonByName
import com.fernandohbrasil.pokemons.domain.interactors.GetPokemonByNameImpl
import com.fernandohbrasil.pokemons.domain.interactors.GetPokemons
import com.fernandohbrasil.pokemons.domain.interactors.GetPokemonsImpl
import org.koin.dsl.module

val pokemonDomainModule = module {
    factory<GetPokemons> { GetPokemonsImpl(get()) }

    factory<GetPokemonByName> { GetPokemonByNameImpl(get()) }
}