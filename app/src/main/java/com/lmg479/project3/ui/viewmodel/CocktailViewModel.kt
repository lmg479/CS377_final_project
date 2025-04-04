package com.lmg479.project3.ui.viewmodel

import androidx.lifecycle.*
import com.lmg479.project3.data.database.model.Cocktail
import com.lmg479.project3.data.repository.CocktailRepository
import kotlinx.coroutines.launch
import com.lmg479.project3.data.database.model.CocktailDto

// view model class
class CocktailViewModel(private val repository: CocktailRepository) : ViewModel() {

    // list of cocktails (random or search results)
    private val _cocktails = MutableLiveData<List<CocktailDto>>()
    val cocktail: LiveData<List<CocktailDto>> = _cocktails

    // favorite cocktails from database
    val favoriteCocktails: LiveData<List<Cocktail>> = repository.getFavoriteCocktails().asLiveData()

    // search cocktails function
    fun searchCocktails(query: String)
    {
        // open the api
        viewModelScope.launch {
            // try and look for Cocktail
            try
            {
                _cocktails.value = repository.searchCocktails(query)
            }
            // if no cocktail then error thrown
            catch (e: Exception)
            {
                e.printStackTrace()
                _cocktails.value = emptyList()
            }
        }
    }

    // get five random cocktails from the api
    fun fetchFiveRandomCocktails()
    {
        // open the view model
        viewModelScope.launch {
            //get random cocktails
            _cocktails.value = repository.fetchFiveRandomCocktails()
        }
    }

    // add the favorited cocktail to database
    fun addToFavorites(cocktailDto: CocktailDto)
    {
        // load the Cocktail information
    viewModelScope.launch {
        val cocktail = Cocktail(
            cocktailId = cocktailDto.idCocktail,
            name = cocktailDto.strCocktail,
            thumbnail = cocktailDto.strCocktailThumb,
            instructions = cocktailDto.strInstructions
        )
        // insert cocktail into the database
        repository.insertCocktail(cocktail)
    }
}


    // remove the cocktail from favorites
    fun removeFromFavorites(cocktail: Cocktail)
    {
        viewModelScope.launch {
            // deletes from database
            repository.deleteCocktail(cocktail)
        }
    }
}
