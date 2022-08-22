package com.fernandohbrasil.pokemons.data.di

import com.fernandohbrasil.pokemons.data.datasource.network.apiservice.PokemonApiService
import com.fernandohbrasil.pokemons.data.datasource.network.apiservice.PokemonApiServiceImpl
import com.fernandohbrasil.pokemons.data.datasource.network.factory.PokemonAPIFactory
import com.fernandohbrasil.pokemons.data.datasource.network.mapper.NetworkToDomainResultPokemonItemMapper
import com.fernandohbrasil.pokemons.data.datasource.network.mapper.NetworkToDomainResultPokemonMapper
import com.fernandohbrasil.pokemons.data.repository.PokemonRepositoryImpl
import com.fernandohbrasil.pokemons.domain.repository.PokemonRepository
import org.koin.dsl.module

var pokemonDataModule = module {
    factory { PokemonAPIFactory.provideOkHttpClient(get()) }
    factory { PokemonAPIFactory.providePokemonApi(get()) }
    factory { PokemonAPIFactory.provideLoggingInterceptor() }
    single { PokemonAPIFactory.provideRetrofit(get()) }

    factory <PokemonApiService> { PokemonApiServiceImpl(get()) }

    factory { NetworkToDomainResultPokemonItemMapper() }
    factory { NetworkToDomainResultPokemonMapper() }

    factory <PokemonRepository> { PokemonRepositoryImpl(get(), get(), get()) }
}