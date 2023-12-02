package com.example.pokedex.network

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("pokemonImage")
fun loadImage(imageView: ImageView, data: String?) {
    imageView.load(data)
}

@BindingAdapter("pokemonName")
fun loadName(textView: TextView, data: String?) {
    textView.text = data
}

@BindingAdapter("pokemonType1")
fun loadType1(textView: TextView, data: List<String>?) {
    textView.text = data?.get(0)
}

@BindingAdapter("pokemonType2")
fun loadType2(textView: TextView, data: List<String>?) {
    if(data?.size == 2){
        textView.text = data[1]
    }
    else{
        textView.text = ""
    }
}

@BindingAdapter("pokemonHeight")
fun loadHeight(textView: TextView, data: String?) {
    textView.text = "$data m"
}

@BindingAdapter("pokemonWeight")
fun loadWeight(textView: TextView, data: String?) {
    textView.text = "$data KG"
}

@BindingAdapter("pokemonDescription")
fun loadDescription(textView: TextView, data: String?) {
    textView.text = data
}