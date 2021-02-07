package com.henryjhavierdev.rickandmorty.parcelable

import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.domain.Location
import com.henryjhavierdev.domain.Origin
import com.henryjhavierdev.rickandmorty.model.CharacterResultRs
import com.henryjhavierdev.rickandmorty.model.LocationRs
import com.henryjhavierdev.rickandmorty.model.OriginRs


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
