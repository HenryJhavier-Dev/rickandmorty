package com.henryjhavierdev.usecases

import com.henryjhavierdev.data.CharacterRepository
import io.reactivex.Maybe

class GetFavoriteCharacterStatusUseCase(
    private val characterRepository: CharacterRepository
) {

    fun invoke(characterId: Int): Maybe<Boolean> {
        return characterRepository.getFavoriteCharacterStatus(characterId)
    }
}
