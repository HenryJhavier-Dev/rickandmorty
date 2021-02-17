package com.henryjhavierdev.rickandmorty.adapters

import com.henryjhavierdev.rickandmorty.parcelables.CharacterResultRs

interface FavoriteListener {
    fun onFavoriteListener(character: CharacterResultRs)
}