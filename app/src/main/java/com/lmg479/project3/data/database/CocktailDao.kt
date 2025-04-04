package com.lmg479.project3.data.database

import androidx.room.*
import com.lmg479.project3.data.database.model.Cocktail
import kotlinx.coroutines.flow.Flow

// database interface
@Dao
interface CocktailDao
{
    // insert cocktail into database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCocktail(cocktail: Cocktail)

    // delete cocktail from database
    @Delete
    suspend fun deleteCocktail(cocktail: Cocktail)

    // grab all cocktails from database
    @Query("SELECT * FROM cocktails")
    fun getAllCocktails(): Flow<List<Cocktail>>
}
