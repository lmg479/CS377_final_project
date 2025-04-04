package com.lmg479.project3.data.database.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

// parcelize the Cocktail data class
@Parcelize
data class CocktailDto(
    @SerializedName("idDrink")
    val idCocktail: String,

    @SerializedName("strDrink")
    val strCocktail: String,

    @SerializedName("strDrinkThumb")
    val strCocktailThumb: String,

    @SerializedName("strInstructions")
    val strInstructions: String
) : Parcelable
