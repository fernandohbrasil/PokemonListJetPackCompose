package com.fernandohbrasil.pokemons.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fernandohbrasil.pokemons.R
import com.fernandohbrasil.pokemons.domain.model.PokemonItem
import com.fernandohbrasil.pokemons.presentation.ui.compose.PokemonItemCompose
import com.fernandohbrasil.pokemons.presentation.viewmodel.ListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(), KoinComponent {

    private val listViewModel: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = Color.White) {
                SetPokemonList(listViewModel.pokemonsLiveData.observeAsState().value)
                listViewModel.loadPokemons()
            }
        }
    }
}

@Composable
private fun SetPokemonList(pokemons: List<PokemonItem>?) {
    Column {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            text = stringResource(id = R.string.app_name),
            fontSize = 26.sp,
        )

        pokemons?.let {
            LazyColumn {
                itemsIndexed(items = it) { _, pokemon ->
                    PokemonItemCompose(pokemonItem = pokemon)
                }
            }
        }
    }
}