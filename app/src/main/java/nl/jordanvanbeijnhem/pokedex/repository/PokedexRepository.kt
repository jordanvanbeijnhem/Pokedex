package nl.jordanvanbeijnhem.pokedex.repository

import nl.jordanvanbeijnhem.pokedex.api.PokedexApi
import nl.jordanvanbeijnhem.pokedex.api.PokedexApiService

class PokedexRepository {

    private val pokedexApi: PokedexApiService = PokedexApi.createApi()

    fun getAllPokemon(paginationOffset: Int) = pokedexApi.getAllPokemon(paginationOffset)

    fun getPokemonInformation(informationUrl: String) = pokedexApi.getPokemonInformation(informationUrl.removePrefix("https://pokeapi.co/api/v2/pokemon/").removeSuffix("/").toInt())
}