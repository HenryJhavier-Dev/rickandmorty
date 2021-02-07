package com.henryjhavierdev.rickandmorty.dataservice

import com.henryjhavierdev.rickandmorty.model.*
import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.domain.Episode
import com.henryjhavierdev.domain.Location
import com.henryjhavierdev.domain.Origin
import com.henryjhavierdev.rickandmorty.database.toLocationDomain
import com.henryjhavierdev.rickandmorty.database.toOriginDomain

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

fun CharacterResultRs.toCharacterDomain(): Character =
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



