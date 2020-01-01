package nl.jordanvanbeijnhem.pokedex.model

import com.google.gson.annotations.SerializedName

data class PokemonInformation (
    val id: Int,
    val sprites: Sprites
) {

}

data class Sprites(
    @SerializedName("back_default") val backSprite: String,
    @SerializedName("front_default") val frontSprite: String
)