package com.henryjhavierdev.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.rickandmorty.viewmodel.FavoriteViewModel.FavoriteListNavigation.ShowCharacterList
import com.henryjhavierdev.rickandmorty.viewmodel.FavoriteViewModel.FavoriteListNavigation.ShowEmptyListMessage
import com.henryjhavierdev.rickandmorty.database.ICharacterDao
import com.henryjhavierdev.rickandmorty.model.CharacterEntity
import com.henryjhavierdev.rickandmorty.presentation.Event
import com.henryjhavierdev.rickandmorty.usecases.GetAllFavoriteCharactersUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteViewModel(
    private  val getAllFavoriteCharactersUseCase: GetAllFavoriteCharactersUseCase
): ViewModel() {

    //region Fields

    private val disposable = CompositeDisposable()

    private val _events = MutableLiveData<Event<FavoriteListNavigation>>()
    val events: LiveData<Event<FavoriteListNavigation>> get() = _events

    private val _favoriteCharacterList: LiveData<List<Character>>
        get() = LiveDataReactiveStreams.fromPublisher(
            getAllFavoriteCharactersUseCase.invoke()
        )

    val favoriteCharacterList: LiveData<List<Character>>
        get() = _favoriteCharacterList

    //endregion

    //region Public Methods

    fun onFavoriteCharacterList(favoriteCharacterList: List<Character>) {
        if (favoriteCharacterList.isEmpty()) {
            _events.value = Event(ShowCharacterList(emptyList()))
            _events.value = Event(ShowEmptyListMessage)
            return
        }

        _events.value = Event(ShowCharacterList(favoriteCharacterList))
    }

    //endregion

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


    sealed class FavoriteListNavigation{
        data class  ShowCharacterList(val characterList: List<Character>): FavoriteListNavigation()
        object ShowEmptyListMessage: FavoriteListNavigation()

    }




}