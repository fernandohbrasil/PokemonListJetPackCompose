package com.fernandohbrasil.pokemons.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.fernandohbrasil.pokemons.domain.interactors.GetPokemonByName
import com.fernandohbrasil.pokemons.domain.model.Pokemon
import com.fernandohbrasil.pokemons.domain.model.PokemonItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListItemViewModel(private val getPokemonByName: GetPokemonByName) {

    private val compositeDisposable = CompositeDisposable()

    val pokemonLiveData = MutableLiveData<Pokemon>()

    fun setModel(pokemonItem: PokemonItem) {
        compositeDisposable.add(
            getPokemonByName(pokemonItem.name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ pokemonLiveData.postValue(it)  }, { Log.e("error", "setModel: ", it) })
        )
    }
}