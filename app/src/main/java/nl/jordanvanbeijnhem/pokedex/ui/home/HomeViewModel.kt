package nl.jordanvanbeijnhem.pokedex.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import nl.jordanvanbeijnhem.pokedex.api.PokedexApi
import nl.jordanvanbeijnhem.pokedex.model.Pokemon
import nl.jordanvanbeijnhem.pokedex.repository.PokedexRepository

class HomeViewModel : ViewModel() {

    private val pokedexRepository = PokedexRepository()
    private var currentPage = 0
    private var isLastPage = false
    val pokemon = MutableLiveData<List<Pokemon>>()
    val loading = MutableLiveData(false)

    fun fetchNextPage() {
        if (!isLastPage) {
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    loading.postValue(true)
                    val pokemonCall =
                        pokedexRepository.getAllPokemon(currentPage * PokedexApi.PAGE_SIZE)
                            .execute()
                    if (pokemonCall.isSuccessful && pokemonCall.body() != null) {
                        val pokemonList = pokemonCall.body()!!.pokemon
                        for (pokemon in pokemonList) {
                            val infoCall =
                                pokedexRepository.getPokemonInformation(pokemon.informationUrl)
                                    .execute()
                            if (infoCall.isSuccessful && infoCall.body() != null) {
                                pokemon.info = infoCall.body()!!
                            }
                        }
                        pokemon.postValue(pokemonList)
                        loading.postValue(false)
                        currentPage++
                        if (pokemonCall.body()!!.next == null) isLastPage = true
                    }
                }
            }
        }
    }
}