package com.henryjhavierdev.rickandmorty.dataservice

import com.henryjhavierdev.rickandmorty.model.*

fun CharacterRs.toCharacterServerList(): List<CharacterResultRs> = results.map {
    it.run{
        CharacterResultRs(
            id,
            name,
            image,
            gender,
            species,
            status,
            origin,
            location,
            episodeList?.map { episode -> "$episode/" }
        )
    }
}

fun CharacterResultRs.toCharacterEntity() = CharacterEntity(
    id,
    name,
    image,
    gender,
    species,
    status,
    origin?.toOriginEntity(),
    location?.toLocationEntity(),
    episodeList ?: emptyList()
)

fun OriginRs.toOriginEntity() = OriginEntity(
    name,
    url
)

fun LocationRs.toLocationEntity() = LocationEntity(
    name,
    url
)
