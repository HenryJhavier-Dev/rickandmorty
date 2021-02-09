package com.henryjhavierdev.rickandmorty.usecases

import com.henryjhavierdev.data.CharacterRepository
import com.henryjhavierdev.rickandmorty.database.ICharacterDao
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetFavoriteCharacterStatusUseCase(
    private val characterRepository: CharacterRepository
) {

    fun invoke(characterId: Int): Maybe<Boolean> {
        return characterRepository.getFavoriteCharacterStatus(characterId)
    }
}
