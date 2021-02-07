package com.henryjhavierdev.rickandmorty.usecases

import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.rickandmorty.database.ICharacterDao
import com.henryjhavierdev.rickandmorty.database.toCharacterDomain
import com.henryjhavierdev.rickandmorty.database.toCharacterDomainList
import com.henryjhavierdev.rickandmorty.model.CharacterEntity
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class GetAllFavoriteCharactersUseCase(
    private val characterDao: ICharacterDao
) {

    fun invoke(): Flowable<List<Character>> = characterDao
        .getAllFavoriteCharacters()
        .map(List<CharacterEntity>::toCharacterDomainList)
        .onErrorReturn { emptyList() }
        .subscribeOn(Schedulers.io())
}
