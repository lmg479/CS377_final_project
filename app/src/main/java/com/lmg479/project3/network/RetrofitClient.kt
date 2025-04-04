package com.lmg479.project3.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// create the retro fit client object
object RetrofitClient
{

    // access the database
    private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

    // call the database
    val apiService: CocktailApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CocktailApiService::class.java)
    }
}
