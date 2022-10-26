package com.datastructblues.pokemonproject.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.datastructblues.pokemonproject.R
import com.datastructblues.pokemonproject.adapter.PokemonAdapter
import com.datastructblues.pokemonproject.databinding.FragmentPokemonBinding
import com.datastructblues.pokemonproject.model.PokemonResponse
import com.datastructblues.pokemonproject.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonFragment : Fragment() {
    private lateinit var binding:FragmentPokemonBinding
    private val viewModel by lazy { ViewModelProvider(this)[PokemonViewModel::class.java] }
    private val pokemonAdapter = PokemonAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("hello world")
        initUI()
    }

    private fun initUI(){
       binding.pokemonListRecycler.layoutManager = LinearLayoutManager(context)
        binding.pokemonListRecycler.adapter = pokemonAdapter
       pokemonAdapter.pokemonClick = { int->
           println("int = ${int}")
           sendData(int)

       }
        println("once")
        viewModel.getPokemonList()
        println("sonra")
        viewModel.pokemonList.observe(viewLifecycleOwner, Observer { list ->
            pokemonAdapter.setData(list)
            println("list = ${list}")
            println("pokemonAdapter = ${pokemonAdapter.pokemonList.size}")
        })
    }
    private fun sendData(result:Int){
        val idData = PokemonFragmentDirections.actionPokemonFragmentToDetailFragment(result)
        Navigation.findNavController(binding.pokemonListRecycler).navigate(idData)

    }
}