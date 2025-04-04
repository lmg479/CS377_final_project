package com.lmg479.project3.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// create the Cocktail data class
@Entity(tableName = "cocktails")
data class Cocktail(
    // set the variables for id, name, image, and instructions
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cocktailId: String,
    val name: String,
    val thumbnail: String,
    val instructions: String
)

