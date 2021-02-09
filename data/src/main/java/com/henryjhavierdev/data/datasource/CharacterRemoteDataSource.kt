package com.henryjhavierdev.data.datasource

import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.domain.Episode
import io.reactivex.Single

interface CharacterRemoteDataSource {
    fun getAllCharacters(page: Int): Single<List<Character>>
}

interface EpisodeRemoteDataSource {
    fun getEpisodeFromCharacter(episodeUrlList: List<String>): Single<List<Episode>>
}
