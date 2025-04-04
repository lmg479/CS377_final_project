package com.lmg479.project3.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lmg479.project3.R
import com.lmg479.project3.data.database.CocktailDatabase
import com.lmg479.project3.data.repository.CocktailRepository
import com.lmg479.project3.data.database.model.CocktailDto
import com.lmg479.project3.ui.adapter.CocktailAdapter
import com.lmg479.project3.ui.viewmodel.CocktailViewModel
import com.lmg479.project3.ui.viewmodel.CocktailViewModelFactory

// build the home screen
class HomeFragment : Fragment() {

    // init variables
    private lateinit var cocktailViewModel: CocktailViewModel

    // get the application ready
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        // get the app ready with Cocktails
        val application = requireActivity().application
        val repository = CocktailRepository(CocktailDatabase.getInstance(application).cocktailDao())
        val factory = CocktailViewModelFactory(repository)
        cocktailViewModel = ViewModelProvider(this, factory)[CocktailViewModel::class.java]

        // make the screen visible
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // get the information ready
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        // get all the buttons ready
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.cocktailRecyclerView)
        val refreshButton = view.findViewById<Button>(R.id.refreshButton)
        val searchEditText = view.findViewById<EditText>(R.id.searchEditText)
        val searchButton = view.findViewById<Button>(R.id.searchButton)

        // get the list put together on the screen
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // observe and update cocktail list
        cocktailViewModel.cocktail.observe(viewLifecycleOwner) { cocktailList ->
            Log.d("HomeFragment", "Cocktail list size: ${cocktailList.size}")

            // set adapter to RecyclerView
            recyclerView.adapter = CocktailAdapter(
                cocktails = cocktailList,
                onCocktailClick = { cocktail -> showCocktailDetails(cocktail) },
                onFavoriteClick = { cocktail ->
                    cocktailViewModel.addToFavorites(cocktail)
                    Toast.makeText(requireContext(), "Added to favorites: ${cocktail.strCocktail}", Toast.LENGTH_SHORT).show()
                }
            )
        }

        // load 5 cocktails on page load
        cocktailViewModel.fetchFiveRandomCocktails()

        // if the page is refreshed then add 5 new Cocktails
        refreshButton.setOnClickListener {
            cocktailViewModel.fetchFiveRandomCocktails()
        }

        // add the search bar
        searchButton.setOnClickListener{
            // make the search bar editable
            val query = searchEditText.text.toString().trim()
            // makes sure the search bar is empty to display text
            if (query.isNotEmpty())
            {
                cocktailViewModel.searchCocktails(query)
            }
            else
            {
                Toast.makeText(requireContext(), "Enter a keyword to search!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // button to go to show details page
    private fun showCocktailDetails(cocktail: CocktailDto)
    {
        // get all the Cocktail information
        val bundle = Bundle().apply{
            putParcelable("cocktail", cocktail)
        }
        val detailFragment = CocktailDetailFragment().apply {
            arguments = bundle
        }

        // push the new information to the details page
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}
