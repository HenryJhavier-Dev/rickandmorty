package com.henryjhavierdev.rickandmorty.database

import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.domain.Location
import com.henryjhavierdev.domain.Origin
import com.henryjhavierdev.rickandmorty.model.*

fun List<CharacterEntity>.toCharacterDomainList() = map(CharacterEntity:: toCharacterDomain)

fun CharacterEntity.toCharacterDomain() = Character(
    id,
    name,
    image,
    gender,
    species,
    status,
    origin?.toOriginDomain(),
    location?.toLocationDomain(),
    episodeList
)

fun OriginEntity.toOriginDomain() = Origin(
    originName ?: "",
    originUrl ?: ""
)

fun LocationEntity.toLocationDomain() = Location(
    locationName ?: "",
    locationUrl ?: ""
)

fun Character.toCharacterEntity() = CharacterEntity(
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

fun Origin.toOriginEntity() = OriginEntity(
    name ?: "",
    url ?: ""
)

fun Location.toLocationEntity() = LocationEntity(
    name ?: "",
    url ?: ""
)

