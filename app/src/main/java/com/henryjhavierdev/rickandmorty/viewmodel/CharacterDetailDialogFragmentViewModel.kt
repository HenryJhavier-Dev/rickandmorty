package com.henryjhavierdev.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.henryjhavierdev.rickandmorty.dataservice.toCharacterEntity
import com.henryjhavierdev.rickandmorty.model.CharacterEntity
import com.henryjhavierdev.rickandmorty.model.CharacterResultRs
import com.henryjhavierdev.rickandmorty.model.EpisodeRs
import com.henryjhavierdev.rickandmorty.presentation.Event
import com.henryjhavierdev.rickandmorty.usecases.GetEpisodeFromCharacterUseCase
import com.henryjhavierdev.rickandmorty.usecases.GetFavoriteCharacterStatusUseCase
import com.henryjhavierdev.rickandmorty.usecases.UpdateFavoriteCharacterStatusUseCase
import io.reactivex.disposables.CompositeDisposable

class CharacterDetailDialogFragmentViewModel(
    private val character: CharacterResultRs? = null,
    private val getEpisodeFromCharacterUseCase: GetEpisodeFromCharacterUseCase,
    private val getFavoriteCharacterStatusUseCase: GetFavoriteCharacterStatusUseCase,
    private val updateFavoriteCharacterStatusUseCase: UpdateFavoriteCharacterStatusUseCase
) : ViewModel() {

    //region Fields
    private val disposable = CompositeDisposable()

    private val _characterValues = MutableLiveData<CharacterResultRs>()
    val characterValues: LiveData<CharacterResultRs> get() = _characterValues

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    private val _events = MutableLiveData<Event<CharacterDetailNavigation>>()
    val events: LiveData<Event<CharacterDetailNavigation>> get() = _events

    //endregion

    //region Override Methods & Callbacks

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    //endregion

    //region Public Methods

    fun onCharacterValidation() {

        if (character == null) {
            _events.value = Event(CharacterDetailNavigation.CloseActivity)
            return
        }

        _characterValues.value = character

        validateFavoriteCharacterStatus(character.id)
        character.episodeList?.let { requestShowEpisodeList(it) }
    }

    fun onUpdateFavoriteCharacterStatus() {
        val characterEntity: CharacterEntity = character!!.toCharacterEntity()

        println(" No pudo guardarlo $characterEntity")

        disposable.add(
           updateFavoriteCharacterStatusUseCase.invoke(characterEntity)
                .subscribe { isFavorite ->
                    _isFavorite.value = isFavorite
                }

        )
    }

    //endregion

    //region Private Methods

    private fun validateFavoriteCharacterStatus(characterId: Int){
        disposable.add(
            getFavoriteCharacterStatusUseCase
                .invoke(characterId)
                .subscribe (
                    { isFavorite ->
                    _isFavorite.value = isFavorite
                    },
                    { error ->
                        println(" No esta en la bd guardado ! osea no es favorito")
                    }
                )

        )
    }

    private fun requestShowEpisodeList(episodeUrlList: List<String>){
        disposable.add(
            getEpisodeFromCharacterUseCase.invoke(episodeUrlList)
                .doOnSubscribe {
                    _events.value = Event(CharacterDetailNavigation.ShowEpisodeListLoading)
                }
                .subscribe(
                    { episodeList ->
                        _events.value = Event(CharacterDetailNavigation.HideEpisodeListLoading)
                        _events.value = Event(CharacterDetailNavigation.ShowEpisodeList(episodeList))
                    },
                    { error ->
                        _events.value = Event(CharacterDetailNavigation.HideEpisodeListLoading)
                        _events.value = Event(CharacterDetailNavigation.ShowEpisodeError(error))
                    })
        )
    }

    //endregion

    //region Inner Classes & Interfaces

    sealed class CharacterDetailNavigation {
        data class ShowEpisodeError(val error: Throwable) : CharacterDetailNavigation()
        data class ShowEpisodeList(val episodeList: List<EpisodeRs>) : CharacterDetailNavigation()
        object CloseActivity : CharacterDetailNavigation()
        object HideEpisodeListLoading : CharacterDetailNavigation()
        object ShowEpisodeListLoading : CharacterDetailNavigation()
    }

    //endregion

}
