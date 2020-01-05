package nl.jordanvanbeijnhem.pokedex.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.android.parcel.Parcelize
import nl.jordanvanbeijnhem.pokedex.database.converter.ObjectConverter

@Parcelize
@Entity(tableName = "pokemon")
@TypeConverters(ObjectConverter::class)
data class Pokemon(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "sprites")
    val sprites: Sprites,

    @ColumnInfo(name = "note")
    var note: Note,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean

) : Parcelable