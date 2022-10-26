package com.datastructblues.pokemonproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.datastructblues.pokemonproject.databinding.ItemPokemonBinding
import com.datastructblues.pokemonproject.model.PokemonResponse

class PokemonAdapter(): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    var pokemonClick: ((String) -> Unit)? = null
    var pokemonList: List<PokemonResponse.PokeResult> = emptyList<PokemonResponse.PokeResult>()

    fun setData(list: List<PokemonResponse.PokeResult>) {
        pokemonList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.name.setOnClickListener {
                pokemonClick?.invoke(pokemonList[adapterPosition].name)
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
