package com.henryjhavierdev.rickandmorty.parcelables

import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.domain.Location
import com.henryjhavierdev.domain.Origin

fun CharacterResultParcelable.toCharacterDomain(): Character =
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

fun OriginParcelable.toOriginDomain() = Origin(
    name ?: "",
    url ?: ""
)

fun LocationParcelable.toLocationDomain() = Location(
    name ?: "",
    url ?: ""
)

fun Character.toCharacterResultParcelable() = CharacterResultParcelable(
    id,
    name,
    image,
    gender,
    species,
    status,
    origin?.toOriginParcelable(),
    location?.toLocationParcelable(),
    episodeList ?: emptyList()
)

fun Origin.toOriginParcelable() = OriginParcelable(
    name ?: "",
    url ?: ""
)

fun Location.toLocationParcelable() = LocationParcelable(
    name ?: "",
    url ?: "")


