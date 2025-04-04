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
import com.lmg479.project3.data.database.model.CocktailDto


// create the cocktail adapter class
class CocktailAdapter(
    // init variables
    private val cocktails: List<CocktailDto>,
    private val onCocktailClick: (CocktailDto) -> Unit,
    private val onFavoriteClick: (CocktailDto) -> Unit
    // access the cocktail information and buttons
) : RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder>() {

    // grab the information from the original page
    inner class CocktailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val imageCocktailThumb: ImageView = itemView.findViewById(R.id.imageCocktailThumb)
        val textCocktailName: TextView = itemView.findViewById(R.id.textCocktailName)
        val btnFavorite: Button = itemView.findViewById(R.id.btnFavorite)
        val btnViewDetails: Button = itemView.findViewById(R.id.btnViewDetails)
    }

    // get the layout from the xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cocktail, parent, false)
        return CocktailViewHolder(view)
    }

    // set the cocktails name and image down as well as the buttons
    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int)
    {
        val cocktail = cocktails[position]
        holder.textCocktailName.text = cocktail.strCocktail
        holder.imageCocktailThumb.load(cocktail.strCocktailThumb)

        holder.btnFavorite.setOnClickListener {
            onFavoriteClick(cocktail)
        }

        holder.btnViewDetails.setOnClickListener {
            onCocktailClick(cocktail)
        }
    }

    // count the numbers of cocktails to make sure it stays in 5
    override fun getItemCount(): Int = cocktails.size
}
