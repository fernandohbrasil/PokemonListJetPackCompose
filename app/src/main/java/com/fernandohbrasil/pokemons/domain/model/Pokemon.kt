package com.fernandohbrasil.pokemons.domain.model

data class Pokemon(
    val url: String,
    val name: String,
    val order: String,
    val types: String,
    val weight: String,
    val height: String
)