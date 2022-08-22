package com.fernandohbrasil.pokemons.core.extensions

import androidx.lifecycle.LiveData
import com.fernandohbrasil.pokemons.core.testutils.LiveDataTestObserver

fun Int.formattedHeight(): String {
    return this.div(10.0).toString() + " M Height"
}

fun Int.formattedWeight() : String {
    return this.div(10.0).toString() + " Kg"
}

fun Int.formattedOrder() = "#${this.toString().padStart(3, '0')}"

fun Int.imageUrlFromId(): String {
    return this.let { "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$it.png" }
}

fun <T> LiveData<T>.test() = LiveDataTestObserver(this)