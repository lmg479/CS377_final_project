package com.lmg479.project3.ui

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import coil.load
import com.lmg479.project3.R
import com.lmg479.project3.data.database.CocktailDatabase
import com.lmg479.project3.data.database.model.Cocktail
import com.lmg479.project3.data.database.model.CocktailDto
import com.lmg479.project3.data.repository.CocktailRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// page that shows the cocktail details
class CocktailDetailFragment : Fragment()
{

    // init variables
    private lateinit var cocktail: CocktailDto

    // grab the cocktail information from the original page
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cocktail = it.getParcelable("cocktail")!!
        }
    }

    // get the layout information from the xml
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        return inflater.inflate(R.layout.fragment_cocktail_detail, container, false)
    }

    // put the information into the layout
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        // add the title of the cocktail
        super.onViewCreated(view, savedInstanceState)
        val textTitle = view.findViewById<TextView>(R.id.textCocktailTitle)
        textTitle.text = cocktail.strCocktail
        // hide the navigation bar
        (activity as AppCompatActivity).supportActionBar?.hide()

        // set the values for the cocktail
        val imageView = view.findViewById<ImageView>(R.id.imageCocktail)
        val instructions = view.findViewById<TextView>(R.id.textInstructions)
        val buttonBack = view.findViewById<Button>(R.id.buttonBack)
        val buttonAddFavorite = view.findViewById<Button>(R.id.buttonAddFavorite)

        // load the image and instructions
        imageView.load(cocktail.strCocktailThumb)
        instructions.text = cocktail.strInstructions

        // load the back button
        buttonBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // load the favorites button
        buttonAddFavorite.setOnClickListener{
            // init variables
            val application = requireActivity().application
            val repository = CocktailRepository(CocktailDatabase.getInstance(application).cocktailDao())

            // push all information to that page
            val cocktailEntity = Cocktail(
                id = 0,
                cocktailId = cocktail.idCocktail,
                name = cocktail.strCocktail,
                thumbnail = cocktail.strCocktailThumb,
                instructions = cocktail.strInstructions
            )
            // saves the cocktail
            CoroutineScope(Dispatchers.IO).launch {
                repository.insertCocktail(cocktailEntity)
            }
            // shows the cocktail is saved
            Toast.makeText(requireContext(), "Added to favorites!", Toast.LENGTH_SHORT).show()
        }
    }

    // brings back the navigation bar
    override fun onDestroyView() {
        super.onDestroyView()
        // restore the app bar when leaving the fragment
        (activity as AppCompatActivity).supportActionBar?.show()
    }
}
