package com.datastructblues.pokemonproject.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.datastructblues.pokemonproject.model.PokeResult
import com.datastructblues.pokemonproject.model.PokemonResponse
import com.datastructblues.pokemonproject.service.PokemonAPIService
import com.datastructblues.pokemonproject.util.Util.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonViewModel : ViewModel() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val service: PokemonAPIService = retrofit.create(PokemonAPIService::class.java)

    val pokemonList = MutableLiveData<List<PokeResult>>()
    val pokemonError = MutableLiveData<Boolean>()
    val pokemonLoading = MutableLiveData<Boolean>()

    fun getPokemonList(){
        val call = service.getPokemonList(20,0)
        println("PokemonViewModel.getPokemonList")
        println("BASE_URL = ${BASE_URL}")

        call.enqueue(object : Callback<PokemonResponse>{
            override fun onResponse(call: Call<PokemonResponse>,response: Response<PokemonResponse>) {
                println("PokemonViewModel.onResponse")
                response.body()?.results?.let { list ->
                    pokemonList.postValue(list)
                }
            }
            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
               /* pokemonError.value = true
                pokemonLoading.value = false
                */
                println("PokemonViewModel.onFailure")
                call.cancel()
            }
        })
    }

}