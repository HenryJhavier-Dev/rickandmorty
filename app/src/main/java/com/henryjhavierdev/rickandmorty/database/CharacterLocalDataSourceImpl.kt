package com.henryjhavierdev.rickandmorty.database

import com.henryjhavierdev.data.datasource.LocalCharacterDataSource
import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.rickandmorty.model.CharacterEntity
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterLocalDataSourceImpl (
    database: CharacterDataBase
): LocalCharacterDataSource {

    private val characterDao by lazy { database.characterDao() }


    override fun getAllFavoriteCharacters(): Flowable<List<Character>> =
        characterDao
            .getAllFavoriteCharacters()
            .map(List<CharacterEntity>::toCharacterDomainList)
            .onErrorReturn { emptyList() }
            .subscribeOn(Schedulers.io())

    override fun getFavoriteCharacterStatus(characterId: Int): Maybe<Boolean> {
        return characterDao.getCharacterById(characterId)
            .isEmpty
            .flatMapMaybe { isEmpty ->
                Maybe.just(!isEmpty)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    override fun updateFavoriteCharacterStatus(character: Character): Maybe<Boolean> {
        val characterEntity = character.toCharacterEntity()
        return characterDao.getCharacterById(characterEntity.id)
            .isEmpty
            .flatMapMaybe { isEmpty ->
                if(isEmpty){
                    characterDao.insertCharacter(characterEntity)
                }else{
                    characterDao.deleteCharacter(characterEntity)
                }
                Maybe.just(isEmpty)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())    }

}