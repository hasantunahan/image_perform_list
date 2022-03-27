package com.example.samuraicase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samuraicase.core.extension.glideWithExtensions
import com.example.samuraicase.network.service.pokeurllist.Pokemon
import kotlinx.android.synthetic.main.pokemon_item.view.*

class MainAdapter(private val pokeList: ArrayList<Pokemon>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    fun refreshData(newReqList: List<Pokemon>) {
        pokeList.clear()
        pokeList.addAll(newReqList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(view!!)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.pokemonItemImage.glideWithExtensions(pokeList[position].sprites.other.home.front_default)
        holder.itemView.pokemonName.text = pokeList[position].name.capitalize()
    }

    override fun getItemCount(): Int {
        return if (pokeList.isEmpty()) {
            0;
        } else {
            pokeList.size
        }
    }
}