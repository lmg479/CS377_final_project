package com.lmg479.project3.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lmg479.project3.data.repository.CocktailRepository

// get the data for the view model
class CocktailViewModelFactory(
    // init variables
    private val repository: CocktailRepository

    // actually build the view model
    ) : ViewModelProvider.Factory
    {
        // check the class
        override fun <T : ViewModel> create(modelClass: Class<T>): T
        {
            // return the Cocktail view model using repo
            if (modelClass.isAssignableFrom(CocktailViewModel::class.java)) {
                return CocktailViewModel(repository) as T
            }
            // if not then throw error
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
