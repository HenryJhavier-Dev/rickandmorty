package com.henryjhavierdev.rickandmorty.dataservice

import com.henryjhavierdev.data.datasource.CharacterRemoteDataSource
import com.henryjhavierdev.data.datasource.EpisodeRemoteDataSource
import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.domain.Episode
import com.henryjhavierdev.rickandmorty.model.CharacterRs
import com.henryjhavierdev.rickandmorty.model.EpisodeRs
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterRemoteDataSourceImpl (
    private val characterRequest: CharacterRequest
): CharacterRemoteDataSource {

    override fun getAllCharacters(page: Int): Single<List<Character>> {
        return characterRequest
            .getService<CharacterService>()
            .getAllCharacters(page)
            .map(CharacterRs::toCharacterDomainList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())    }

}

class EpisodeRemoteDataSourceImpl (
    private val episodeRequest: EpisodeRequest
): EpisodeRemoteDataSource {

    override fun getEpisodeFromCharacter(episodeUrlList: List<String>): Single<List<Episode>> {
        return Observable.fromIterable(episodeUrlList)
            .flatMap { episode: String ->
                episodeRequest.URL_BASE = episode
                episodeRequest
                    .getService<EpisodeService>()
                    .getEpisode()
                    .map(EpisodeRs::toEpisodeDomain)
                    .toObservable()
            }
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    }

}