package com.example.samuraicase.network.service.pokeurllist

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprites: Sprites,
)


data class Sprites(
    @SerializedName("other")
    val other: Other
)


data class Other(
    @SerializedName("home")
    val home: Home
)

data class Home(
    @SerializedName("front_default")
    val front_default: String,
    @SerializedName("front_shiny")
    val front_shiny: String,
)