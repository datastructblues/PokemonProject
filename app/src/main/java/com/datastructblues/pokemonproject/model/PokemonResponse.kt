package com.datastructblues.pokemonproject.model

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokeResult>
) {
    data class PokeResult(
        val name: String,
        val url: String
    )
}