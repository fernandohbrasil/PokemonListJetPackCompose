package com.fernandohbrasil.pokemons.data.datasource.network.model

import androidx.annotation.Keep

@Keep
data class NetworkType(
	val name: String?,
	val url: String?
)