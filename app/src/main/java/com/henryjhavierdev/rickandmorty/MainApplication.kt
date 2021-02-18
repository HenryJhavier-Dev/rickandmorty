package com.henryjhavierdev.rickandmorty

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.henryjhavierdev.databasemanager.CharacterDataBase
import com.henryjhavierdev.rickandmorty.di.DaggerRickAndMortyComponent
import com.henryjhavierdev.rickandmorty.di.RickAndMortyComponent

class MainApplication: Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    //val database by lazy { CharacterDataBase.getInstanceDataBase(this) }
    //val dataSource by lazy { CharacterRepository(database.characterDao()) }

    //region Fields

    lateinit var component: RickAndMortyComponent
        private set

    //endregion

    //region Override Methods & Callbacks

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        component = initAppComponent()
    }

    //endregion

    //region Private Methods

    private fun initAppComponent() = DaggerRickAndMortyComponent.factory().create(this)

    //endregion

}