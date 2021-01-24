package com.henryjhavierdev.rickandmorty.viewmodel.home

import android.app.Application
import androidx.lifecycle.*
import com.henryjhavierdev.rickandmorty.model.Character
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private var _characters = MutableLiveData<Character>()
    val characters: LiveData<Character>
        get() = _characters

    init {
        getAllCharacters()
    }
    fun getAllCharacters() = viewModelScope.launch {

        _characters.value = repository.getCharacters()
    }


    class HomeViewModelFactory(private val appication: Application) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(
                    repository = HomeRepository(
                        homeDataSource = HomeDataSource(appication)
                    )
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}

