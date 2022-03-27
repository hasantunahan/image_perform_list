package com.example.samuraicase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var mainAdapter = MainAdapter(arrayListOf())
    private var pokemonLimit = 11
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        intervalList()
        setPokemonList()
    }

    private fun intervalList() {
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                if (pokemonLimit < 1114) {
                    mainAdapter = MainAdapter(arrayListOf())
                    pokemonRecylerView.adapter = mainAdapter
                    mainViewModel.getPokeList(pokemonLimit..pokemonLimit + 11)
                }
                pokemonLimit += 11
                mainHandler.postDelayed(this, 5000)
            }
        })
    }


    private fun setPokemonList() {
        layoutManager = GridLayoutManager(applicationContext, 3)
        pokemonRecylerView.layoutManager = layoutManager
        pokemonRecylerView.adapter = mainAdapter
        renderPokemon()
    }

    private fun renderPokemon() {
        mainViewModel.pokemonList.observe(this, Observer { pokemon ->
            pokemon?.let {
                mainAdapter.refreshData(pokemon)
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