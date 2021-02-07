package com.henryjhavierdev.rickandmorty.usecases

import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.rickandmorty.database.ICharacterDao
import com.henryjhavierdev.rickandmorty.database.toCharacterEntity
import com.henryjhavierdev.rickandmorty.model.CharacterEntity
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UpdateFavoriteCharacterStatusUseCase (
    private val characterDao: ICharacterDao
    ) {

    fun invoke(character: Character): Maybe<Boolean> {
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
            .subscribeOn(Schedulers.io())
    }
}