package com.example.samuraicase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private val adapter = MainAdapter(arrayListOf())
    private val pokemonLimit = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.getPokeList(pokemonLimit)
        setPokemonList()
    }


    private fun setPokemonList() {
        pokemonRecylerView.layoutManager = GridLayoutManager(applicationContext,3)
        pokemonRecylerView.adapter = adapter
        renderPokemon()
    }

    private fun renderPokemon() {
        mainViewModel.pokemonList.observe(this, Observer { pokemon ->
            pokemon?.let {
                adapter.refreshData(pokemon)
                pokemonRecylerView.visibility = View.VISIBLE
                progressBarPokemonList.visibility = View.GONE
            }
        })

        mainViewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                progressBarPokemonList.visibility = View.VISIBLE
                pokemonRecylerView.visibility = View.GONE
            }
        })
    }


}