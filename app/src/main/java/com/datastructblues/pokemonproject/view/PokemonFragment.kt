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
        binding.pokemonList.adapter = pokemonAdapter
        initUI()
    }

    private fun initUI(){
       binding.pokemonList.layoutManager = LinearLayoutManager(context)
       pokemonAdapter.pokemonClick = { strName->
           sendData(strName)

       }

        viewModel.getPokemonList()

        viewModel.pokemonList.observe(viewLifecycleOwner, Observer { list ->
            pokemonAdapter.setData(list)
        })
    }

    private fun sendData(pokemonName:String){
        val action = PokemonFragmentDirections.actionPokemonFragmentToDetailFragment(pokemonName)
        Navigation.findNavController(binding.pokemonList).navigate(action)
    }
}