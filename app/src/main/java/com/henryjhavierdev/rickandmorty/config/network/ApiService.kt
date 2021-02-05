package com.henryjhavierdev.rickandmorty.config.network


import com.henryjhavierdev.rickandmorty.model.Character
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(): Character

    @GET("character/?")
    fun getAllCharacters(
        @Query("page") page: Int
    ): Single<Character>

}