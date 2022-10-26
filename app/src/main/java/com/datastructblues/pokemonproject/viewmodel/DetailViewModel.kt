package com.datastructblues.pokemonproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.datastructblues.pokemonproject.model.PokemonModel
import com.datastructblues.pokemonproject.service.PokemonAPIService
import com.datastructblues.pokemonproject.util.Util.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailViewModel : ViewModel(){
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokemonAPIService = retrofit.create(PokemonAPIService::class.java)

    val pokemonInfo = MutableLiveData<PokemonModel>()

    fun getPokemonInfo(id: Int){
        val call = service.getPokemonInfo(id)

        call.enqueue(object : Callback<PokemonModel> {
            override fun onResponse(call: Call<PokemonModel>, response: Response<PokemonModel>) {
                response.body()?.let { pokemon ->
                    pokemonInfo.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<PokemonModel>, t: Throwable) {
                call.cancel()
            }

        })
    }
}