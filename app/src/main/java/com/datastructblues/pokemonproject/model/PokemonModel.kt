package com.datastructblues.pokemonproject.model


data class PokemonModel(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites
)

data class Sprites (
    val front_default: String?,
    val back_default: String?
)


