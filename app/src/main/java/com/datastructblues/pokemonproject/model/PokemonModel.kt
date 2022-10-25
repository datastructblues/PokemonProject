package com.datastructblues.pokemonproject.model

data class PokemonModel(
    val forms: List<Form>,
    val height: Int,
    val weight: Int
) {
    data class Form(
        val name: String,
        val url: String
    )
}