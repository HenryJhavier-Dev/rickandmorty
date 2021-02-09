package com.henryjhavierdev.usecases

import com.henryjhavierdev.data.CharacterRepository
import com.henryjhavierdev.domain.Character
import io.reactivex.Flowable

class GetAllFavoriteCharactersUseCase(
    private val characterRepository: CharacterRepository
) {

    fun invoke(): Flowable<List<Character>>{
        return characterRepository.getAllFavoriteCharacters()
    }
}
