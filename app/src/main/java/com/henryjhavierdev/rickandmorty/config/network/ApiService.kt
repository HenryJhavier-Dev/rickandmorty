package com.henryjhavierdev.rickandmorty.config.network

import androidx.lifecycle.LiveData
import com.henryjhavierdev.rickandmorty.model.Character
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    suspend fun getCharacters():  Character

}