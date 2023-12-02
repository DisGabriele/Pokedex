package com.example.pokedex

import com.example.pokedex.network.Pokemon
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonList (
    @Json(name = "pokemons") val pokemons : List<Pokemon>
)
