package com.henryjhavierdev.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.henryjhavierdev.rickandmorty.dataservice.CharacterRequest
import com.henryjhavierdev.rickandmorty.dataservice.network.CharacterService
import com.henryjhavierdev.rickandmorty.dataservice.toCharacterServerList
import com.henryjhavierdev.rickandmorty.model.CharacterResultRs
import com.henryjhavierdev.rickandmorty.model.CharacterRs
import com.henryjhavierdev.rickandmorty.presentation.Event
import com.henryjhavierdev.rickandmorty.usecases.GetAllCharactersUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

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
            getAllCharactersUseCase.invoke(currentPage)
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

