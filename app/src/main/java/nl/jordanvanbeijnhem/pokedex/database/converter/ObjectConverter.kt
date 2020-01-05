package nl.jordanvanbeijnhem.pokedex.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import nl.jordanvanbeijnhem.pokedex.model.Note
import nl.jordanvanbeijnhem.pokedex.model.Sprites

class ObjectConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToNote(data: String): Note {
        return gson.fromJson(data, Note::class.java)
    }

    @TypeConverter
    fun stringToSprites(data: String): Sprites {
        return gson.fromJson(data, Sprites::class.java)
    }

    @TypeConverter
    fun objectToString(obj: Any?): String? {
        return gson.toJson(obj)
    }
}