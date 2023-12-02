package com.example.pokedex.network.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.network.Pokemon
import com.example.pokedex.network.PokemonApi
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    private val _pokemonScreen = MutableLiveData(Pokemon(494,"Victini", listOf("Psico","Fuoco"),"0.4","4","Questo Pokèmon porta la vittoria. Si dice che gli Allenatori con Victini vincono sempre, indipendentemente dal tipo di incontro","https://www.serebii.net/pokemon/art/494.png"))
    val pokemonScreen: LiveData<Pokemon> = _pokemonScreen

    fun setPokemonScreen(pokemon: Pokemon){
        _pokemonScreen.value = pokemon
    }


    private fun getPkmnList(){
        viewModelScope.launch {
            try {
                _pokemonList.value = PokemonApi.retrofitService.getPokemon().pokemons.toMutableList()
            } catch (e: Exception) {
                _pokemonList.value = mutableListOf()
            }
        }
    }
    init{
        getPkmnList()
    }

}