package nl.jordanvanbeijnhem.pokedex.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import nl.jordanvanbeijnhem.pokedex.model.Pokemon

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): LiveData<List<Pokemon>>

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun getPokemonById(id: Long): Pokemon

    @Query("SELECT * FROM pokemon WHERE isFavorite = 1")
    fun getFavoritePokemon(): LiveData<List<Pokemon>>

    @Insert
    suspend fun insertPokemon(pokemon: Pokemon)

    @Delete
    suspend fun deletePokemon(pokemon: Pokemon)

    @Update
    suspend fun updatePokemon(pokemon: Pokemon)
}