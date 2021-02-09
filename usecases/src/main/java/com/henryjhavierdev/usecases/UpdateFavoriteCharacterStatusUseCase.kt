package com.henryjhavierdev.usecases

import com.henryjhavierdev.data.CharacterRepository
import com.henryjhavierdev.domain.Character
import io.reactivex.Maybe

class UpdateFavoriteCharacterStatusUseCase (
    private val characterRepository: CharacterRepository
    ) {

    fun invoke(character: Character): Maybe<Boolean> {
      return  characterRepository.updateFavoriteCharacterStatus(character)
    }
}