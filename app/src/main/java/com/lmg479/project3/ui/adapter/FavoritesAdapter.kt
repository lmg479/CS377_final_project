package com.lmg479.project3.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lmg479.project3.R
import com.lmg479.project3.data.database.model.Cocktail

// create the favorite adapter class
class FavoritesAdapter(
    // init variables
    private var cocktails: List<Cocktail>,
    private val onViewDetailsClick: (Cocktail) -> Unit,
    private val onRemoveClick: (Cocktail) -> Unit
    // remove the favorite cocktail from the list
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    // holds a cocktail in the list
    inner class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        // accesses all of the information from the cocktail
        val cocktailImage: ImageView = itemView.findViewById(R.id.imageCocktailThumb)
        val cocktailTitle: TextView = itemView.findViewById(R.id.textCocktailName)

        // accesses the view details and remove buttons
        val btnViewDetails: Button = itemView.findViewById(R.id.btnViewDetails)
        val btnRemove: Button = itemView.findViewById(R.id.btnFavorite)
    }

    // creates a cocktail to show in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder
    {
        // access the layout
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cocktail, parent, false)
        // return the favorites into the layout
        return FavoritesViewHolder(view)
    }

  // puts the cocktail into each part of the list
    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int)
    {
        // displayes the cocktail name and image
        val cocktail = cocktails[position]
        holder.cocktailTitle.text = cocktail.name
        holder.cocktailImage.load(cocktail.thumbnail)

        // displayes the buttons
        holder.btnViewDetails.setOnClickListener {
            onViewDetailsClick(cocktail)
        }
        holder.btnRemove.text = "Remove"
        holder.btnRemove.setOnClickListener {
            onRemoveClick(cocktail)
        }
    }

    // count the number of cocktails
    override fun getItemCount(): Int = cocktails.size

    // update cocktails if new ones are added
    fun updateCocktails(newcocktails: List<Cocktail>)
    {
        cocktails = newcocktails
        notifyDataSetChanged()
    }
}
