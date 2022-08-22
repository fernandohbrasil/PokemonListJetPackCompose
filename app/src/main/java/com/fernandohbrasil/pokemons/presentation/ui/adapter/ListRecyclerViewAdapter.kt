package com.fernandohbrasil.pokemons.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.fernandohbrasil.pokemons.databinding.ItemListBinding
import com.fernandohbrasil.pokemons.domain.model.PokemonItem
import com.fernandohbrasil.pokemons.presentation.ui.adapter.viewholder.ListItemViewHolder

class ListRecyclerViewAdapter : ListAdapter<PokemonItem, ListItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListItemViewHolder {
        return ListItemViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: ListItemViewHolder, position: Int) {
        viewHolder.onBind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PokemonItem>() {
            override fun areItemsTheSame(
                oldPokemonItem: PokemonItem,
                newPokemonItem: PokemonItem
            ): Boolean {
                return oldPokemonItem.name == newPokemonItem.name
            }

            override fun areContentsTheSame(
                oldPokemonItem: PokemonItem,
                newPokemonItem: PokemonItem
            ): Boolean {
                return oldPokemonItem == newPokemonItem
            }
        }
    }
}
