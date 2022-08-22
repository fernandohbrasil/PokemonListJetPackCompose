package com.fernandohbrasil.pokemons.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fernandohbrasil.pokemons.BR
import com.fernandohbrasil.pokemons.databinding.ActivityMainBinding
import com.fernandohbrasil.pokemons.domain.model.PokemonItem
import com.fernandohbrasil.pokemons.presentation.ui.adapter.ListRecyclerViewAdapter
import com.fernandohbrasil.pokemons.presentation.viewmodel.ListViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : AppCompatActivity(), KoinComponent {

    private lateinit var binding: ActivityMainBinding
    private val listViewModel: ListViewModel by viewModel()
    private val listRecyclerViewAdapter: ListRecyclerViewAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.setVariable(BR.viewModel, listViewModel)
        binding.executePendingBindings()
        binding.lifecycleOwner = this
        setContentView(binding.root)

        supportActionBar?.hide()

        setUpAdapter()

        listViewModel.pokemonsLiveData.observe(this as LifecycleOwner, pokemonsLiveDataObserver())
        listViewModel.error.observe(this as LifecycleOwner, errorObserver())

        listViewModel.loadPokemons()
    }

    private fun setUpAdapter() {
        binding.activityMainListRv.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = listRecyclerViewAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun pokemonsLiveDataObserver(): Observer<List<PokemonItem>> = Observer {
        listRecyclerViewAdapter.submitList(it)
    }

    private fun errorObserver(): Observer<String> = Observer {
        Snackbar.make(binding.root, it, Snackbar.LENGTH_INDEFINITE).show()
    }
}