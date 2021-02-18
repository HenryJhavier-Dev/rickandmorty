package com.henryjhavierdev.rickandmorty.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.rickandmorty.parcelables.CharacterResultParcelable
import com.henryjhavierdev.rickandmorty.presentation.Event
import com.henryjhavierdev.usecases.GetAllCharactersUseCase
import io.reactivex.disposables.CompositeDisposable

//Los casos de uso son los que manejan la logica
// y los view model solo sirven de intermediarios
class HomeViewModel(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
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

    fun onLoadMoreItems(visibleItemCount: Int, firstVisibleItemPosition: Int,
                        totalItemCount: Int) {
        if (isLoading || isLastPage ||
            !isInFooter(visibleItemCount, firstVisibleItemPosition, totalItemCount)) {
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
        Log.i("HomeVM","currentPage $currentPage")
        disposable.add(
            getAllCharactersUseCase
                .invoke(currentPage)
                .doOnSubscribe {
                            isLoading = true
                            _events.value = Event(CharacterListNavigation.ShowLoading)
                        }
                        .subscribe({ characterList ->
                            if (characterList.size < PAGE_SIZE) {
                                isLastPage = true
                            }
                            isLoading = false
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

    private fun isInFooter(
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
        data class ShowCharacterList(val characterList: List<Character>) : CharacterListNavigation()
        object HideLoading : CharacterListNavigation()
        object ShowLoading : CharacterListNavigation()
    }

    private var _characters = MutableLiveData<CharacterResultParcelable>()
    val characters: LiveData<CharacterResultParcelable>
        get() = _characters




    companion object {

        private const val PAGE_SIZE = 20

    }

    //endregion


}

