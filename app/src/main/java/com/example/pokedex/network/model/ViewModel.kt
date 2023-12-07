package com.example.pokedex.network.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.network.Pokemon
import com.example.pokedex.network.PokemonApi
import kotlinx.coroutines.launch

enum class Status{
    LOADING,ERROR,DONE
}

class PokemonViewModel : ViewModel() {

    private val _statusCounter = MutableLiveData(false)
    val statusCounter: LiveData<Boolean> = _statusCounter


    private val _status = MutableLiveData(Status.LOADING)
    val status: LiveData<Status> = _status

    private val _pokemonList = MutableLiveData<List<Pokemon>>(listOf())
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    private val _pokemonScreen = MutableLiveData<Pokemon>()
    val pokemonScreen: LiveData<Pokemon> = _pokemonScreen

    fun setPokemonScreen(pokemon: Pokemon){
        _pokemonScreen.value = pokemon
    }

    fun toggleStatusCounter(){
        _statusCounter.value = !_statusCounter.value!!
    }


    private fun getPkmnList(){
        viewModelScope.launch {
            while(_pokemonList.value!!.isEmpty()) {
                try {
                    _pokemonList.value = PokemonApi.retrofitService.getPokemon().pokemons.toMutableList()
                } catch (e: Exception) {
                    _pokemonList.value = mutableListOf()
                    _status.value = Status.ERROR
                }
            }
            _status.value = Status.DONE
        }
    }
    init{
        getPkmnList()
    }

}