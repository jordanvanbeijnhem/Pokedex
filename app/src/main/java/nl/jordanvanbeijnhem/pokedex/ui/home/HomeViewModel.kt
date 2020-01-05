package nl.jordanvanbeijnhem.pokedex.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.jordanvanbeijnhem.pokedex.api.PokedexApi
import nl.jordanvanbeijnhem.pokedex.model.Note
import nl.jordanvanbeijnhem.pokedex.model.Pokemon
import nl.jordanvanbeijnhem.pokedex.repository.PokedexRepository
import nl.jordanvanbeijnhem.pokedex.repository.PokemonRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val pokedexRepository = PokedexRepository()
    private val pokemonRepository = PokemonRepository(application.applicationContext)
    private var currentPage = 0
    private var isLastPage = false
    val fetchedPokemon: LiveData<List<Pokemon>> = pokemonRepository.getAllPokemon()
    val loading = MutableLiveData(false)

    fun fetchNextPage() {
        if (!isLastPage) {
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.IO) {
                    loading.postValue(true)

                    for (i in currentPage * PokedexApi.PAGE_SIZE + 1..currentPage * PokedexApi.PAGE_SIZE + PokedexApi.PAGE_SIZE) {
                        var pokemon = pokemonRepository.getPokemonById(i.toLong())
                        if (pokemon == null) {
                            val infoCall =
                                pokedexRepository.getPokemonById(i)
                                    .execute()
                            if (infoCall.isSuccessful && infoCall.body() != null) {
                                pokemon = infoCall.body()!!
                                pokemon.id = i.toLong()
                                pokemon.note = Note("")
                                pokemon.isFavorite = false
                                pokemonRepository.insertPokemon(pokemon)
                            } else {
                                isLastPage = true
                                break
                            }
                        }
                    }

                    loading.postValue(false)
                    currentPage++
                }
            }
        }
    }
}