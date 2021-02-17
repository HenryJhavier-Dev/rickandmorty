package com.henryjhavierdev.rickandmorty.adapters

import com.henryjhavierdev.rickandmorty.parcelables.CharacterResultParcelable

interface CharacterListener {
    fun openCharacterDetail(character: CharacterResultParcelable)
}