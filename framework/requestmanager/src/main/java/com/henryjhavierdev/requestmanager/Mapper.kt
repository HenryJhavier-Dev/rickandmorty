package com.henryjhavierdev.requestmanager

import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.domain.Episode
import com.henryjhavierdev.domain.Location
import com.henryjhavierdev.domain.Origin


fun CharacterRs.toCharacterDomainList(): List<Character> = results.map {
    it.run{
        Character(
            id,
            name,
            image,
            gender,
            species,
            status,
            origin?.toOriginDomain(),
            location?.toLocationDomain(),
            episodeList?.map { episode -> "$episode/" } ?: emptyList()
        )
    }
}

fun OriginRs.toOriginDomain() = Origin(
    name ?: "",
    url ?: ""
)

fun LocationRs.toLocationDomain() = Location(
    name ?: "",
    url ?: ""
)

fun EpisodeRs.toEpisodeDomain() = Episode(
    id_episode,
    name_episode
)




