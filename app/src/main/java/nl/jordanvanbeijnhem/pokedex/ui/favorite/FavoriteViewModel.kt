package nl.jordanvanbeijnhem.pokedex.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.jordanvanbeijnhem.pokedex.model.Pokemon
import nl.jordanvanbeijnhem.pokedex.repository.PokemonRepository

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val pokemonRepository = PokemonRepository(application.applicationContext)
    val favoritePokemon: LiveData<List<Pokemon>> = pokemonRepository.getFavoritePokemon()
    val loading = MutableLiveData(false)
}