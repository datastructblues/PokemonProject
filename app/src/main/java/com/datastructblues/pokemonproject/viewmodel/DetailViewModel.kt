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
import java.io.IOException

class DetailViewModel : ViewModel(){
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokemonAPIService = retrofit.create(PokemonAPIService::class.java)

    val pokemonInfo = MutableLiveData<PokemonModel>()
    val detailLoading = MutableLiveData<Boolean>(true)

    fun getPokemonInfo(id: Int){
        val call = service.getPokemonInfo(id)

        call.enqueue(object : Callback<PokemonModel> {
            override fun onResponse(call: Call<PokemonModel>, response: Response<PokemonModel>) {
                response.body()?.let { pokemon ->
                    pokemonInfo.postValue(pokemon)
                }
                detailLoading.value = false
            }

            override fun onFailure(call: Call<PokemonModel>, t: Throwable) {
                detailLoading.value = false
                call.cancel()
                //sends data to crashlytics if data on not loaded on detail fragment.
                throw Exception("Data have not loaded.")
            }

        })
    }
}