package com.henryjhavierdev.rickandmorty.viewmodel.home

import androidx.lifecycle.LiveData
import com.henryjhavierdev.rickandmorty.config.network.ServiceBuilder
import com.henryjhavierdev.rickandmorty.model.Character

class HomeRepository(private val homeDataSource: HomeDataSource) {

    suspend fun getCharacters(): Character {
        val result = ServiceBuilder.dataAccess.getCharacters()

        if (result.results.isNotEmpty()) {
            setCharactersInRoom(result)
        }

        return result

    }

    private fun setCharactersInRoom(result: Character) {
        homeDataSource.insert()
        // todo persistence data using room
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

}