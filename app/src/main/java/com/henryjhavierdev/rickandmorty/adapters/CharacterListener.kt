package com.henryjhavierdev.rickandmorty.adapters

import com.henryjhavierdev.rickandmorty.parcelables.CharacterResultRs

interface CharacterListener {
    fun openCharacterDetail(character: CharacterResultRs)
}