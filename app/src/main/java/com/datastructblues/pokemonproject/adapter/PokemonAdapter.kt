package com.datastructblues.pokemonproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.datastructblues.pokemonproject.databinding.ItemPokemonBinding
import com.datastructblues.pokemonproject.model.PokeResult
import com.datastructblues.pokemonproject.model.PokemonResponse

class PokemonAdapter(): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    var pokemonClick: ((Int) -> Unit)? = null
    var pokemonList: List<PokeResult> = emptyList()

    //Gelen pokemon list adapter'e set edildi
    fun setData(list: List<PokeResult>) {
        pokemonList = list
        notifyDataSetChanged()
    }
    //viewholder icinde click olaylari (pokemon fragmenttan yonetiliyor)
    inner class ViewHolder(val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.name.setOnClickListener {
                pokemonClick?.invoke((adapterPosition+1))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.binding.name.text = pokemon.name
    }
}
