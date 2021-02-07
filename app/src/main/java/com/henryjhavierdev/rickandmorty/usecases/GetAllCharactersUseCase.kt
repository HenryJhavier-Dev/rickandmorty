package com.henryjhavierdev.rickandmorty.usecases

import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.rickandmorty.dataservice.CharacterRequest
import com.henryjhavierdev.rickandmorty.dataservice.network.CharacterService
import com.henryjhavierdev.rickandmorty.dataservice.toCharacterDomainList
import com.henryjhavierdev.rickandmorty.model.CharacterRs
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class GetAllCharactersUseCase(
    private val characterRequest: CharacterRequest) {

    fun invoke(currentPage: Int): Single<List<Character>> = characterRequest
        .getService<CharacterService>()
        .getAllCharacters(currentPage)
        .map(CharacterRs::toCharacterDomainList)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}