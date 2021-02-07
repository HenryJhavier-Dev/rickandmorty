package com.henryjhavierdev.rickandmorty.database

import com.henryjhavierdev.rickandmorty.model.*


fun CharacterEntity.toCharacterRs() = CharacterResultRs(
    id,
    name,
    image,
    gender,
    species,
    status,
    origin?.toOriginServer(),
    location?.toLocationServer(),
    episodeList
)

fun OriginEntity.toOriginServer() = OriginRs(
    originName,
    originUrl
)

fun LocationEntity.toLocationServer() = LocationRs(
    locationName,
    locationUrl
)
