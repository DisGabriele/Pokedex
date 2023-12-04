package com.example.pokedex.network

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.pokedex.R

@BindingAdapter("pokemonImage")
fun loadImage(imageView: ImageView, data: String?) {
    imageView.load(data){
        error(R.drawable.missingno)
        placeholder(R.drawable.pok__ball_placeholder_icon)
    }
}

@BindingAdapter("pokemonName")
fun loadName(textView: TextView, data: String?) {
    textView.text = data
}

@BindingAdapter("pokemonType1")
fun loadType1(textView: TextView, data: List<String>?) {
    textView.text = data?.get(0)

    textView.setBackgroundResource(when(textView.text){
        "Normale" -> R.color.type_normal
        "Fuoco" -> R.color.type_fire
        "Acqua" -> R.color.type_water
        "Elettro" -> R.color.type_electric
        "Erba" -> R.color.type_grass
        "Ghiaccio" -> R.color.type_ice
        "Lotta" -> R.color.type_fighting
        "Veleno" -> R.color.type_poison
        "Terra" -> R.color.type_ground
        "Volante" -> R.color.type_flying
        "Psico" -> R.color.type_psychic
        "Coleott." -> R.color.type_bug
        "Roccia" -> R.color.type_rock
        "Spettro" -> R.color.type_ghost
        "Drago" -> R.color.type_dragon
        "Acciaio" -> R.color.type_steel
        "Buio" -> R.color.type_dark
        "Folletto" -> R.color.type_fairy
        else -> R.color.white
    })

}

@BindingAdapter("pokemonType2")
fun loadType2(textView: TextView, data: List<String>?) {
    if(data?.size == 2){
        textView.text = data[1]

        textView.setBackgroundResource(when(textView.text){
            "Normale" -> R.color.type_normal
            "Fuoco" -> R.color.type_fire
            "Acqua" -> R.color.type_water
            "Elettro" -> R.color.type_electric
            "Erba" -> R.color.type_grass
            "Ghiaccio" -> R.color.type_ice
            "Lotta" -> R.color.type_fighting
            "Veleno" -> R.color.type_poison
            "Terra" -> R.color.type_ground
            "Volante" -> R.color.type_flying
            "Psico" -> R.color.type_psychic
            "Coleott." -> R.color.type_bug
            "Roccia" -> R.color.type_rock
            "Spettro" -> R.color.type_ghost
            "Drago" -> R.color.type_dragon
            "Acciaio" -> R.color.type_steel
            "Buio" -> R.color.type_dark
            "Folletto" -> R.color.type_fairy
            else -> R.color.white
        })
    }
    else{
        textView.text = ""
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("pokemonHeight")
fun loadHeight(textView: TextView, data: String?) {
    textView.text = "Altezza: $data m"
}

@SuppressLint("SetTextI18n")
@BindingAdapter("pokemonWeight")
fun loadWeight(textView: TextView, data: String?) {
    textView.text = "Peso: $data KG"
}

@BindingAdapter("pokemonDescription")
fun loadDescription(textView: TextView, data: String?) {
    textView.text = data
}