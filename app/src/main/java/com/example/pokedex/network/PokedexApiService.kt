package com.example.pokedex.network

import com.example.pokedex.PokemonList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val URL : String = "https://raw.githubusercontent.com/DisGabriele/Unova-Pokedex/main/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(URL)
    .build()


interface PokedexApiService {
    @GET("UnovaPokedex.json")
    suspend fun getPokemon() : PokemonList

}
object PokemonApi {
    val retrofitService: PokedexApiService by lazy{
        retrofit.create(PokedexApiService::class.java)
    }
}