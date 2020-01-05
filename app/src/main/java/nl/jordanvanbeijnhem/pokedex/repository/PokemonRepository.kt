package nl.jordanvanbeijnhem.pokedex.repository

import android.content.Context
import androidx.lifecycle.LiveData
import nl.jordanvanbeijnhem.pokedex.database.PokemonRoomDatabase
import nl.jordanvanbeijnhem.pokedex.model.Pokemon

class PokemonRepository(context: Context) {

    private var pokemonDao = PokemonRoomDatabase.getDatabase(context)!!.pokemonDao()

    fun getAllPokemon(): LiveData<List<Pokemon>> {
        return pokemonDao.getAllPokemon()
    }

    fun getPokemonById(id: Long): Pokemon? {
        return pokemonDao.getPokemonById(id)
    }

    fun getFavoritePokemon(): LiveData<List<Pokemon>> {
        return pokemonDao.getFavoritePokemon()
    }

    suspend fun insertPokemon(pokemon: Pokemon) {
        pokemonDao.insertPokemon(pokemon)
    }

    suspend fun deletePokemon(pokemon: Pokemon) {
        pokemonDao.deletePokemon(pokemon)
    }

    suspend fun updatePokemon(pokemon: Pokemon) {
        pokemonDao.updatePokemon(pokemon)
    }
}