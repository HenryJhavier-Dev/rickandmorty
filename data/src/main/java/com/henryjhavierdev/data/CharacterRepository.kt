package com.henryjhavierdev.data

import com.henryjhavierdev.data.datasource.CharacterRemoteDataSource
import com.henryjhavierdev.data.datasource.EpisodeRemoteDataSource
import com.henryjhavierdev.data.datasource.LocalCharacterDataSource
import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.domain.Episode
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

class CharacterRepository (
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val characterLocalDataSource: LocalCharacterDataSource
    ) {

        //region Public Methods

        fun getAllCharacters(page: Int): Single<List<Character>> =
            characterRemoteDataSource.getAllCharacters(page)

        fun getAllFavoriteCharacters(): Flowable<List<Character>> =
            characterLocalDataSource.getAllFavoriteCharacters()

        fun getFavoriteCharacterStatus(characterId: Int): Maybe<Boolean> =
            characterLocalDataSource.getFavoriteCharacterStatus(characterId)

        fun updateFavoriteCharacterStatus(character: Character): Maybe<Boolean> =
            characterLocalDataSource.updateFavoriteCharacterStatus(character)

        //endregion
    }

class EpisodeRepository(
        private val remoteEpisodeDataSource: EpisodeRemoteDataSource
    ) {

        //region Public Methods

        fun getEpisodeFromCharacter(episodeUrlList: List<String>): Single<List<Episode>> =
            remoteEpisodeDataSource.getEpisodeFromCharacter(episodeUrlList)

        //endregion
    }
