package nl.jordanvanbeijnhem.pokedex.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(

    var content: String

) : Parcelable