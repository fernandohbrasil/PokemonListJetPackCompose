package com.fernandohbrasil.pokemons.presentation.ui.adapter.viewholder

import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.fernandohbrasil.pokemons.BR
import com.fernandohbrasil.pokemons.databinding.ItemListBinding
import com.fernandohbrasil.pokemons.domain.model.PokemonItem
import com.fernandohbrasil.pokemons.presentation.ui.adapter.viewmodel.ListItemViewModel
import org.koin.core.component.inject
import org.koin.core.component.KoinComponent

class ListItemViewHolder(binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root), KoinComponent {

    private val listItemViewModel: ListItemViewModel by inject()

    init {
        binding.setVariable(BR.viewModel, listItemViewModel)
        binding.executePendingBindings()
        binding.lifecycleOwner = (binding.root.context as FragmentActivity)
    }

    fun onBind(pokemonItem: PokemonItem) {
        listItemViewModel.setModel(pokemonItem)
    }
}