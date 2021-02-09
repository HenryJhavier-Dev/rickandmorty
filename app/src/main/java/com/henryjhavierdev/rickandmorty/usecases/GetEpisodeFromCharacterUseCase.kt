package com.henryjhavierdev.rickandmorty.usecases

import com.henryjhavierdev.data.EpisodeRepository
import com.henryjhavierdev.domain.Episode
import io.reactivex.Single

class GetEpisodeFromCharacterUseCase(
    private val episodeRepository: EpisodeRepository
) {

    fun invoke(episodeUrlList: List<String>): Single<List<Episode>> {
        return episodeRepository.getEpisodeFromCharacter(episodeUrlList)
    }
}
