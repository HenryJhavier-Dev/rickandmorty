package com.henryjhavierdev.rickandmorty.usecases

import com.henryjhavierdev.data.CharacterRepository
import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.rickandmorty.database.ICharacterDao
import com.henryjhavierdev.rickandmorty.database.toCharacterDomain
import com.henryjhavierdev.rickandmorty.database.toCharacterDomainList
import com.henryjhavierdev.rickandmorty.model.CharacterEntity
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class GetAllFavoriteCharactersUseCase(
    private val characterRepository: CharacterRepository
) {

    fun invoke(): Flowable<List<Character>>{
        return characterRepository.getAllFavoriteCharacters()
    }
}
