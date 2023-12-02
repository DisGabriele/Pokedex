package com.example.pokedex.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon (
    @Json(name = "num") var num: Int,
    @Json(name = "name") var name: String,
    @Json(name = "types") var types: List<String>,
    @Json(name = "height") var height: String,
    @Json(name = "weight") var weight: String,
    @Json(name = "description") var description: String,
    @Json(name = "img") var img: String,
)

