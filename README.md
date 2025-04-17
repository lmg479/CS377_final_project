# Cocktail Explorer

Cocktail Explorer is an Android application built with Kotlin that enables users to search for cocktails, view detailed information, and manage a list of favorite drinks. The app uses data from TheCocktailDB API and stores favorites locally using Room.

## Features

- Search for cocktails by name using a public REST API
- Fetch and display five random cocktails
- View detailed information for each cocktail, including image and instructions
- Add or remove cocktails from a favorites list
- Persist favorite cocktails locally using Room database
- Built with MVVM architecture using Retrofit, Coroutines, and LiveData

## Architecture

The application follows the Model–View–ViewModel (MVVM) architecture pattern:

- **Model**: Defines the data layer including Room entities, DAO interfaces, and Retrofit API services
- **ViewModel**: Provides UI-related data to the view, handles data fetching logic, and exposes data using LiveData and Flow
- **View**: Fragments that observe data from ViewModels and display it to the user

## Requirements

- Android Studio (Arctic Fox or newer)
- Android SDK version 33 or higher
- Gradle 7.0 or higher
- Internet connection for API access

## Setup Instructions

1. Clone the repository:
git clone https://github.com/lmg479/CS377_final_project.git cd CS377_final_project

2. Open the project in Android Studio.

3. Allow Gradle to sync and install dependencies.

4. Run the application on an emulator or Android device running API level 33 or higher.

## Dependencies

- Retrofit (for network requests)
- Room (for local database)
- Gson (for JSON serialization)
- Kotlin Coroutines and Flow (for asynchronous and reactive programming)
- AndroidX Navigation Components (for screen transitions)
- LiveData and ViewModel (for lifecycle-aware UI updates)

## Data Source

All cocktail data is sourced from TheCocktailDB API: https://www.thecocktaildb.com/api.php

## Project Structure

├── data/
│   ├── api/          # Retrofit service
│   ├── db/           # Room entities, DAO, database
├── ui/
│   ├── home/
│   ├── detail/
│   ├── favorites/
├── viewmodel/
├── model/
├── MainActivity.kt
