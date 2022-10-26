package com.datastructblues.pokemonproject.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.datastructblues.pokemonproject.R
import com.datastructblues.pokemonproject.databinding.FragmentDetailBinding
import com.datastructblues.pokemonproject.viewmodel.DetailViewModel
import com.datastructblues.pokemonproject.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var bindingDetail: FragmentDetailBinding
    private val viewModel by lazy { ViewModelProvider(this)[DetailViewModel::class.java] }

    val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingDetail = FragmentDetailBinding.inflate(inflater, container, false)
        return bindingDetail.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val idFromList= getIdData()
        initUI(idFromList)

    }

    private fun getIdData():Int{
        var id =0
        arguments?.let {
            id = DetailFragmentArgs.fromBundle(it).id
        }
        return id
    }


    private fun initUI(listId:Int){
        viewModel.getPokemonInfo(listId)
        viewModel.pokemonInfo.observe(viewLifecycleOwner) { pokemon ->
            bindingDetail.pokemonName.text = "Pokemon name: ${pokemon.name}"
            bindingDetail.pokemonHeight.text= "Pokemon height: ${pokemon.height}"
            bindingDetail.pokemonWeight.text = "Pokemon weight: ${pokemon.weight}"

            Glide.with(this).load(pokemon.sprites.front_default).into(bindingDetail.frontImage)
            Glide.with(this).load(pokemon.sprites.back_default).into(bindingDetail.backImage)
        }
    }
}