package com.lmg479.project3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lmg479.project3.R
import com.lmg479.project3.data.database.model.CocktailDto
import com.lmg479.project3.data.database.CocktailDatabase
import com.lmg479.project3.data.database.model.Cocktail
import com.lmg479.project3.data.repository.CocktailRepository
import com.lmg479.project3.ui.adapter.FavoritesAdapter
import com.lmg479.project3.ui.viewmodel.CocktailViewModel
import com.lmg479.project3.ui.viewmodel.CocktailViewModelFactory

// create the favorite class page
class FavoritesFragment : Fragment()
{
    // grabs the cocktails and shows them in a list
    private lateinit var cocktailViewModel: CocktailViewModel
    private lateinit var favoritesAdapter: FavoritesAdapter

    // set up the view model
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        // get the application and create the helpers
        val application = requireActivity().application
        val repository = CocktailRepository(CocktailDatabase.getInstance(application).cocktailDao())
        val factory = CocktailViewModelFactory(repository)
        cocktailViewModel = ViewModelProvider(this, factory)[CocktailViewModel::class.java]

        // actually show the favorites screen
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    // Creating and showing the lists
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        // finding the list
        val recyclerView = view.findViewById<RecyclerView>(R.id.favoritesRecyclerView)

        // create the buttons
        favoritesAdapter = FavoritesAdapter(
            emptyList(),
            onRemoveClick = ::removeFromFavorites,
            onViewDetailsClick = ::showCocktailDetails
        )

        // actually hooking up the lists
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = favoritesAdapter

        // update the list with new cocktails and manage the favorites
        cocktailViewModel.favoriteCocktails.observe(viewLifecycleOwner) { cocktails ->
            favoritesAdapter.updateCocktails(cocktails)
        }
    }

    // remove from favorite
    private fun removeFromFavorites(cocktail: Cocktail)
    {
        // show that the cocktail was removed
        cocktailViewModel.removeFromFavorites(cocktail)
        Toast.makeText(requireContext(), "Removed from favorites", Toast.LENGTH_SHORT).show()
    }

    // go to detail page
   private fun showCocktailDetails(cocktail: Cocktail)
   {
       // get all the Cocktail information
    val cocktailDto = CocktailDto(
        idCocktail = cocktail.cocktailId,
        strCocktail = cocktail.name,
        strCocktailThumb = cocktail.thumbnail,
        strInstructions = cocktail.instructions
    )

    // make a package out of the information
    val bundle = Bundle().apply {
        putParcelable("cocktail", cocktailDto)
    }

    // transport the package to the new screen
    val detailFragment = CocktailDetailFragment().apply {
        arguments = bundle
    }

    // go to new screen and update package
    requireActivity().supportFragmentManager.beginTransaction()
        .replace(R.id.nav_host_fragment, detailFragment)

        // back button
        .addToBackStack(null)
        .commit()
}

}


