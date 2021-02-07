package com.henryjhavierdev.rickandmorty.adapters

import com.henryjhavierdev.rickandmorty.model.CharacterEntity
import com.henryjhavierdev.rickandmorty.model.CharacterResultRs

interface FavoriteListener {
    fun onFavoriteListener(character: CharacterEntity)
}