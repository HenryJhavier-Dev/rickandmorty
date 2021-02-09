package com.henryjhavierdev.rickandmorty.usecases

import com.henryjhavierdev.data.CharacterRepository
import com.henryjhavierdev.domain.Character
import io.reactivex.Single


class GetAllCharactersUseCase(
    private val characterRepository: CharacterRepository
    ){

    fun invoke(currentPage: Int): Single<List<Character>>{
      return  characterRepository.getAllCharacters(currentPage)
    }
}