package com.henryjhavierdev.rickandmorty.adapters

import com.henryjhavierdev.rickandmorty.parcelables.CharacterResultParcelable


interface FavoriteListener {
    fun onFavoriteListener(character: CharacterResultParcelable)
}