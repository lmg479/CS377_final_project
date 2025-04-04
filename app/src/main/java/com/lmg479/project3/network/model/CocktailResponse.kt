package com.lmg479.project3.network.model

import com.google.gson.annotations.SerializedName
import com.lmg479.project3.data.database.model.CocktailDto

// access the cocktaildto information
data class CocktailResponse(
    @SerializedName("drinks")
    val cocktails: List<CocktailDto>?
)