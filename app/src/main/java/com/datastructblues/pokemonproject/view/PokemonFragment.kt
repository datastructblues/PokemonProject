package com.datastructblues.pokemonproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
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
        observe()
        swipe()
    }

    private fun observe(){
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

            list?.let {
                binding.pokemonListRecycler.visibility = View.VISIBLE
                pokemonAdapter.setData(it)
            }

            println("list = ${list}")
            println("pokemonAdapter = ${pokemonAdapter.pokemonList.size}")
        })

        viewModel.pokemonError.observe(viewLifecycleOwner){ error ->
            error?.let {
                if(it){
                    binding.pokemonError.visibility = View.VISIBLE
                }else{
                    binding.pokemonError.visibility = View.GONE
                }
            }
        }

        viewModel.pokemonLoading.observe(viewLifecycleOwner){ loading ->
            loading?.let {
                if(it){
                    binding.pokemonLoading.visibility = View.VISIBLE
                    binding.pokemonListRecycler.visibility = View.GONE
                    binding.pokemonError.visibility = View.GONE
                }else{
                    binding.pokemonLoading.visibility = View.GONE
                }
            }
        }
    }
    private fun sendData(result:Int){
        val idData = PokemonFragmentDirections.actionPokemonFragmentToDetailFragment(result)
        Navigation.findNavController(binding.pokemonListRecycler).navigate(idData)
    }
    private fun swipe(){
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.pokemonError.visibility = View.GONE
            binding.pokemonListRecycler.visibility = View.GONE
            binding.pokemonLoading.visibility = View.VISIBLE
            viewModel.getPokemonList()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}