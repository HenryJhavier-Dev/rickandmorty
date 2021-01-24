package com.henryjhavierdev.rickandmorty.viewmodel.home

import android.app.Application
import com.henryjhavierdev.rickandmorty.MainApplication
import com.henryjhavierdev.rickandmorty.database.CharacterDataBase

class HomeDataSource(private val application: Application)
{
    init {
        val prueba = (application as MainApplication).database
    }

    fun insert(){

    }
}