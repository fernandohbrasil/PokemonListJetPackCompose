package com.fernandohbrasil.pokemons.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.fernandohbrasil.pokemons.core.presentation.viewmodel.BaseViewModel
import com.fernandohbrasil.pokemons.domain.interactors.GetPokemons
import com.fernandohbrasil.pokemons.domain.model.PokemonItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListViewModel(
    private val getPokemons: GetPokemons
) : BaseViewModel() {

    var pokemonsLiveData = MutableLiveData<List<PokemonItem>>()

    fun loadPokemons() {
        compositeDisposable.add(
            getPokemons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ pokemonsLiveData.postValue(it) }, { Log.e("error", "loadPokemons: ", it)})
        )
    }
}