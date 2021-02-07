package com.henryjhavierdev.rickandmorty.adapters

import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.rickandmorty.model.CharacterResultRs

interface CharacterListener {
    fun openCharacterDetail(character: CharacterResultRs)
}