package nl.jordanvanbeijnhem.pokedex.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    @SerializedName("results") val pokemon: List<Pokemon>
)