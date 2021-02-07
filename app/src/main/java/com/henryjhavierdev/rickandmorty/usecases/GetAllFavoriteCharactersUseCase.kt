package com.henryjhavierdev.rickandmorty.usecases

import com.henryjhavierdev.rickandmorty.database.ICharacterDao
import com.henryjhavierdev.rickandmorty.model.CharacterEntity
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class GetAllFavoriteCharactersUseCase(
    private val characterDao: ICharacterDao
) {

    fun invoke(): Flowable<List<CharacterEntity>> = characterDao
        .getAllFavoriteCharacters()
        .onErrorReturn { emptyList() }
        .subscribeOn(Schedulers.io())
}
