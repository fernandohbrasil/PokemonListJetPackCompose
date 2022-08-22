package com.fernandohbrasil.pokemons.presentation.ui.adapter.viewmodel

import androidx.lifecycle.MutableLiveData
import com.fernandohbrasil.pokemons.domain.interactors.GetPokemonByName
import com.fernandohbrasil.pokemons.domain.model.Pokemon
import com.fernandohbrasil.pokemons.domain.model.PokemonItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListItemViewModel(private val getPokemonByName: GetPokemonByName) {

    private val compositeDisposable = CompositeDisposable()

    val isLoading = MutableLiveData(false)
    val error = MutableLiveData(false)
    val pokemonLiveData = MutableLiveData<Pokemon>()

    fun setModel(pokemonItem: PokemonItem) {
        isLoading.postValue(true)

        compositeDisposable.add(
            getPokemonByName(pokemonItem.name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ handleGetPokemonByNameSuccess(it) }, { handleGetPokemonByNameError() })
        )
    }

    private fun handleGetPokemonByNameError() {
        isLoading.postValue(false)
        error.postValue(true)
    }

    private fun handleGetPokemonByNameSuccess(pokemon: Pokemon?) {
        isLoading.postValue(false)
        pokemonLiveData.postValue(pokemon)
    }
}