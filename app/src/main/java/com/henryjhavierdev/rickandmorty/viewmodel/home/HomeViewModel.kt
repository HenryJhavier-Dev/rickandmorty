package com.henryjhavierdev.rickandmorty.viewmodel.home

import androidx.lifecycle.*
import com.henryjhavierdev.rickandmorty.config.network.ApiService
import com.henryjhavierdev.rickandmorty.config.network.CharacterRequest
import com.henryjhavierdev.rickandmorty.config.network.toCharacterList
import com.henryjhavierdev.rickandmorty.model.Character
import com.henryjhavierdev.rickandmorty.model.CharacterResultRs
import com.henryjhavierdev.rickandmorty.presentation.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val characterRequest: CharacterRequest
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _events = MutableLiveData<Event<CharacterListNavigation>>()
    val events: LiveData<Event<CharacterListNavigation>> get() = _events

    private var currentPage = 1
    private var isLastPage = false
    private var isLoading = false


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
    //region Private Methods

    fun onLoadMoreItems(visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int) {
        if (isLoading || isLastPage || !isInFooter(visibleItemCount, firstVisibleItemPosition, totalItemCount)) {
            return
        }

        currentPage += 1
        onGetAllCharacters()
    }

    fun onRetryGetAllCharacter(itemCount: Int) {
        if (itemCount > 0) {
            _events.value = Event(CharacterListNavigation.HideLoading)
            return
        }

        onGetAllCharacters()
    }




    fun onGetAllCharacters() {

        disposable.add(
            characterRequest
                        .getService<ApiService>()
                        .getAllCharacters(currentPage)
                        .map(Character::toCharacterList)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe {
                            isLoading = true
                            _events.value = Event(CharacterListNavigation.ShowLoading)
                        }
                        .subscribe({ characterList ->
                            if (characterList.size < PAGE_SIZE) {
                                isLastPage = true
                            }
                            _events.value = Event(CharacterListNavigation.HideLoading)
                            _events.value = Event(CharacterListNavigation.ShowCharacterList(characterList))
                        },
                        { error ->
                            isLastPage = true
                            isLoading = false
                            _events.value = Event(CharacterListNavigation.HideLoading)
                            _events.value = Event(CharacterListNavigation.ShowCharacterError(error))
                        })
        )
    }

    fun isInFooter(
        visibleItemCount: Int,
        firstVisibleItemPosition: Int,
        totalItemCount: Int
    ): Boolean {
        return visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE
    }
    //endregion

    sealed class CharacterListNavigation {
        data class ShowCharacterError(val error: Throwable) : CharacterListNavigation();
        data class ShowCharacterList(val characterList: List<CharacterResultRs>) : CharacterListNavigation()
        object HideLoading : CharacterListNavigation()
        object ShowLoading : CharacterListNavigation()
    }

    private var _characters = MutableLiveData<CharacterResultRs>()
    val characters: LiveData<CharacterResultRs>
        get() = _characters




    companion object {

        private const val PAGE_SIZE = 20

    }

    //endregion


}

