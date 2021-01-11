package com.henryjhavierdev.rickandmorty.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.adapters.DataBindingCharacterAdapter
import com.henryjhavierdev.rickandmorty.data.MediaProvider
import com.henryjhavierdev.rickandmorty.model.Result
import kotlinx.coroutines.launch

class NotificationsViewModel : ViewModel() {

    private var recyclerPhoneAdapter: DataBindingCharacterAdapter? = null

    private val _characters = MutableLiveData<List<Result>>()
    var characters: LiveData<List<Result>> = _characters

    private val _text = MutableLiveData<String>().apply {
        value = "Using Data Binding"
    }
    val text: LiveData<String> = _text

    init {
        loadCharacters()
    }

    private fun loadCharacters()= viewModelScope.launch {
        _characters.value = MediaProvider.loadCharacter().results
    }

    //usado en el fragmento
    fun setRecyclerAdapter(characters: List<Result>){
        recyclerPhoneAdapter?.setCharacterList(characters)
        recyclerPhoneAdapter?.notifyDataSetChanged()

    }

    //utilizado en el xml del fragmento
    fun getRecyclerAdapter(): DataBindingCharacterAdapter?{
        recyclerPhoneAdapter = DataBindingCharacterAdapter( this,
            layoutInflate = R.layout.view_character_item_data_binding)
        return recyclerPhoneAdapter
    }

    fun getCharacter(position: Int): Result? {
        val phones: List<Result>? = characters.value
        return phones?.get(position)
    }
}