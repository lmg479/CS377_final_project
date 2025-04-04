package com.lmg479.project3.data.repository

import android.util.Log
import com.lmg479.project3.data.database.CocktailDao
import com.lmg479.project3.data.database.model.Cocktail
import com.lmg479.project3.data.database.model.CocktailDto
import com.lmg479.project3.network.RetrofitClient
import kotlinx.coroutines.flow.Flow

class CocktailRepository(private val cocktailDao: CocktailDao) {

    // fetch 5 random cocktails
    suspend fun fetchFiveRandomCocktails(): List<CocktailDto> {
    // return the list once 5 cocktails have been grabbed
    return buildList {
        repeat(5) { _ ->
            try {
                // access the api and grab a cocktail
                val response = RetrofitClient.apiService.getRandomCocktail()

                // add this log to check the API response
                Log.d("CocktailRepository", "API response: ${response.cocktails}")

                val cocktail = response.cocktails?.firstOrNull()
                cocktail?.let { add(it) }
            } catch (e: Exception) {
                Log.e("API Error", "Error fetching data: ${e.message}")
            }
        }
    }
}


    // search cocktails by keyword
    suspend fun searchCocktails(query: String): List<CocktailDto> {
    return try {
        val response = RetrofitClient.apiService.searchCocktails(query)

        // add this log to check the API response
        Log.d("CocktailRepository", "API response: ${response.cocktails}")

        response.cocktails ?: emptyList()
    } catch (e: Exception) {
        Log.e("API Error", "Error fetching data: ${e.message}")
        emptyList()
    }
}

    // insert cocktail into favorites
    suspend fun insertCocktail(cocktail: Cocktail)
    {
        cocktailDao.insertCocktail(cocktail)
    }

    // get all favorite cocktails
    fun getFavoriteCocktails(): Flow<List<Cocktail>>
    {
        return cocktailDao.getAllCocktails()
    }

    // remove cocktail from favorites
    suspend fun deleteCocktail(cocktail: Cocktail)
    {
        cocktailDao.deleteCocktail(cocktail)
    }

}

