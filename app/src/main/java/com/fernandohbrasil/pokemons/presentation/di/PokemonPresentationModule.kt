package com.fernandohbrasil.pokemons.presentation.di

import com.fernandohbrasil.pokemons.presentation.ui.adapter.ListRecyclerViewAdapter
import com.fernandohbrasil.pokemons.presentation.ui.adapter.viewmodel.ListItemViewModel
import com.fernandohbrasil.pokemons.presentation.viewmodel.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokemonPresentationModule = module {
    viewModel { ListViewModel(get()) }

    factory { ListRecyclerViewAdapter() }
    factory { ListItemViewModel(get()) }
}