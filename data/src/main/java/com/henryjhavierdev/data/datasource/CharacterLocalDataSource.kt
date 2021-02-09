package com.henryjhavierdev.data.datasource

import com.henryjhavierdev.domain.Character
import io.reactivex.Flowable
import io.reactivex.Maybe


interface LocalCharacterDataSource {

    fun getAllFavoriteCharacters(): Flowable<List<Character>>

    fun getFavoriteCharacterStatus(characterId: Int): Maybe<Boolean>

    fun updateFavoriteCharacterStatus(character: Character): Maybe<Boolean>
}