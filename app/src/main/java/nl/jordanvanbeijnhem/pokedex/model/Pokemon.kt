package nl.jordanvanbeijnhem.pokedex.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val name: String,
    @SerializedName("url") val informationUrl: String,
    var info: PokemonInformation,
    var isFavorite: Boolean = false
)