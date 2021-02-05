package com.henryjhavierdev.rickandmorty.config.network

import com.henryjhavierdev.rickandmorty.model.CharacterResultRs
import com.henryjhavierdev.rickandmorty.model.Character

fun Character.toCharacterList(): List<CharacterResultRs> = results.map {
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
            episodeList.map { episode -> "$episode/" }
        )
    }
}

/*
fun CharacterServer.toCharacterEntity() = CharacterEntity(
    id,
    name,
    image,
    gender,
    species,
    status,
    origin.toOriginEntity(),
    location.toLocationEntity(),
    episodeList
)

fun OriginServer.toOriginEntity() = OriginEntity(
    name,
    url
)

fun LocationServer.toLocationEntity() = LocationEntity(
    name,
    url
)*/
