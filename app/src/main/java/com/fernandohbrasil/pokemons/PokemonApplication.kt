package com.fernandohbrasil.pokemons

import android.app.Application
import com.fernandohbrasil.pokemons.data.di.pokemonDataModule
import com.fernandohbrasil.pokemons.domain.di.pokemonDomainModule
import com.fernandohbrasil.pokemons.presentation.di.pokemonPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokemonApplication : Application() {

    override fun onCreate() {
        startKoin {
            androidContext(this@PokemonApplication)
            modules(listOf(pokemonDataModule, pokemonDomainModule, pokemonPresentationModule))
        }

        super.onCreate()
    }
}