package com.lmg479.project3.data.database

import android.app.Application
import com.lmg479.project3.data.repository.CocktailRepository

// create the meal application class
class CocktailApp : Application()
{
    // get the repository
    lateinit var repository: CocktailRepository
        private set

    // open the database and manipulate it
    override fun onCreate()
    {
        super.onCreate()

        val database = CocktailDatabase.getInstance(this)
        repository = CocktailRepository(
            cocktailDao = database.cocktailDao()
        )
    }
}