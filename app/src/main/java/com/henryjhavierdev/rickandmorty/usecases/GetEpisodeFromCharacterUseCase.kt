package com.henryjhavierdev.rickandmorty.usecases

import com.henryjhavierdev.domain.*
import com.henryjhavierdev.rickandmorty.dataservice.EpisodeRequest
import com.henryjhavierdev.rickandmorty.dataservice.network.EpisodeService
import com.henryjhavierdev.rickandmorty.dataservice.toEpisodeDomain
import com.henryjhavierdev.rickandmorty.model.EpisodeRs
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetEpisodeFromCharacterUseCase(
    private val episodeRequest: EpisodeRequest
) {

    fun invoke(episodeUrlList: List<String>): Single<List<Episode>> {
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