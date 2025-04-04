package com.lmg479.project3.network

import com.lmg479.project3.network.model.CocktailResponse
import retrofit2.http.GET
import retrofit2.http.Query

// create the cocktailapiservice interface
interface CocktailApiService
{
    // generates a random cocktail from the api
    @GET("random.php")
    suspend fun getRandomCocktail(): CocktailResponse

    // searches the api
    @GET("search.php")
    suspend fun searchCocktails(@Query("s") query: String): CocktailResponse
}
