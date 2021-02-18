package com.henryjhavierdev.rickandmorty.di

import com.henryjhavierdev.rickandmorty.viewmodel.HomeViewModel
import com.henryjhavierdev.usecases.GetAllCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class CharacterListModule {

    @Provides
    fun characterListViewModelProvider(
        getAllCharactersUseCase: GetAllCharactersUseCase
    ) = HomeViewModel(
        getAllCharactersUseCase
    )
}

@Subcomponent(modules = [(CharacterListModule::class)])
interface CharacterListComponent {
    val characterListViewModel: HomeViewModel
}
