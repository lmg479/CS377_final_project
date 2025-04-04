package com.lmg479.project3.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lmg479.project3.data.database.model.Cocktail

// database class
@Database(entities = [Cocktail::class], version = 1, exportSchema = false)
abstract class CocktailDatabase : RoomDatabase() {

    // get the database helper
    abstract fun cocktailDao(): CocktailDao

    // manipulate the database
    companion object {
        @Volatile
        private var INSTANCE: CocktailDatabase? = null

        fun getInstance(context: Context): CocktailDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CocktailDatabase::class.java,
                    "cocktail_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
