package com.datastructblues.pokemonproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.datastructblues.pokemonproject.databinding.ItemPokemonBinding

class PokemonAdapter: RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    class ViewHolder(val binding:ItemPokemonBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}