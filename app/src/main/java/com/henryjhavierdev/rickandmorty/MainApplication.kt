package com.henryjhavierdev.rickandmorty

import android.app.Application
import com.henryjhavierdev.databasemanager.CharacterDataBase

class MainApplication: Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { CharacterDataBase.getInstanceDataBase(this) }
    //val dataSource by lazy { CharacterRepository(database.characterDao()) }

}