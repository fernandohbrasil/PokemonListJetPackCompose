package com.fernandohbrasil.pokemons.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.fernandohbrasil.pokemons.core.presentation.viewmodel.BaseViewModel
import com.fernandohbrasil.pokemons.domain.interactors.GetPokemons
import com.fernandohbrasil.pokemons.domain.model.PokemonItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListViewModel(private val getPokemons: GetPokemons) : BaseViewModel() {

    val isLoading = MutableLiveData(false)
    val error = MutableLiveData<String>()
    val pokemonsLiveData = MutableLiveData<List<PokemonItem>>()

    fun loadPokemons() {
        isLoading.postValue(true)

        compositeDisposable.add(
            getPokemons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ handleLoadPokemonsSuccess(it) }, { handleLoadPokemonsError(it) })
        )
    }

    private fun handleLoadPokemonsSuccess(pokemons: List<PokemonItem>) {
        isLoading.postValue(false)
        pokemonsLiveData.postValue(pokemons)
    }

    private fun handleLoadPokemonsError(throwable: Throwable) {
        isLoading.postValue(false)
        error.postValue(throwable.message.orEmpty())
    }
}