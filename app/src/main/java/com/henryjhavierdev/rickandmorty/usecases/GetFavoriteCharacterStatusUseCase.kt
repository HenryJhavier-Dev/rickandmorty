package com.henryjhavierdev.rickandmorty.usecases

import com.henryjhavierdev.rickandmorty.database.ICharacterDao
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetFavoriteCharacterStatusUseCase(
    private val characterDao: ICharacterDao
) {

    fun invoke(characterId: Int): Maybe<Boolean> {
        return characterDao.getCharacterById(characterId)
            .isEmpty
            .flatMapMaybe { isEmpty ->
                Maybe.just(!isEmpty)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}
