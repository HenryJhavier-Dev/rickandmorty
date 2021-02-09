package com.henryjhavierdev.rickandmorty.usecases

import com.henryjhavierdev.data.CharacterRepository
import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.rickandmorty.database.ICharacterDao
import com.henryjhavierdev.rickandmorty.database.toCharacterEntity
import com.henryjhavierdev.rickandmorty.model.CharacterEntity
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UpdateFavoriteCharacterStatusUseCase (
    private val characterRepository: CharacterRepository
    ) {

    fun invoke(character: Character): Maybe<Boolean> {
      return  characterRepository.updateFavoriteCharacterStatus(character)
    }
}