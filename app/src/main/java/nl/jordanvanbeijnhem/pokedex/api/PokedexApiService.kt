package nl.jordanvanbeijnhem.pokedex.api

import nl.jordanvanbeijnhem.pokedex.model.Pokemon
import nl.jordanvanbeijnhem.pokedex.model.PokemonInformation
import nl.jordanvanbeijnhem.pokedex.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApiService {

    @GET("pokemon/?limit=50")
    fun getAllPokemon(@Query("offset") paginationOffset: Int): Call<PokemonResponse>

    @GET("pokemon/{id}")
    fun getPokemonInformation(@Path("id") pokemonId: Int): Call<PokemonInformation>
}