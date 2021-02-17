package com.henryjhavierdev.rickandmorty.parcelable

import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.domain.Location
import com.henryjhavierdev.domain.Origin
import com.henryjhavierdev.rickandmorty.parcelables.CharacterResultRs
import com.henryjhavierdev.rickandmorty.parcelables.LocationRs
import com.henryjhavierdev.rickandmorty.parcelables.OriginRs


fun Character.toCharacterResultRs() = CharacterResultRs(
    id,
    name,
    image,
    gender,
    species,
    status,
    origin?.toOriginRs(),
    location?.toLocationRs(),
    episodeList ?: emptyList()
)

fun Origin.toOriginRs() = OriginRs(
    name ?: "",
    url ?: ""
)

fun Location.toLocationRs() = LocationRs(
    name ?: "",
    url ?: ""
)
