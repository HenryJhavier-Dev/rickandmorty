package com.henryjhavierdev.rickandmorty.config.network


import com.henryjhavierdev.rickandmorty.model.CharacterRs
import com.henryjhavierdev.rickandmorty.model.EpisodeRs
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(): CharacterRs

    @GET("character/?")
    fun getAllCharacters(
        @Query("page") page: Int
    ): Single<CharacterRs>

}


interface EpisodeService {

    @GET(".")
    fun getEpisode(): Single<EpisodeRs>
}
