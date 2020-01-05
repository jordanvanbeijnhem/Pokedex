package nl.jordanvanbeijnhem.pokedex.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sprites(

    @SerializedName("back_default") val backSprite: String,

    @SerializedName("front_default") val frontSprite: String

) : Parcelable