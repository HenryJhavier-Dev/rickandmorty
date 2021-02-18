package com.henryjhavierdev.rickandmorty.di

import com.henryjhavierdev.rickandmorty.viewmodel.FavoriteViewModel
import com.henryjhavierdev.usecases.GetAllFavoriteCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class FavoriteListModule {

    @Provides
    fun favoriteListViewModelProvider(
        getAllFavoriteCharactersUseCase: GetAllFavoriteCharactersUseCase
    ) = FavoriteViewModel(
        getAllFavoriteCharactersUseCase
    )
}

@Subcomponent(modules = [(FavoriteListModule::class)])
interface FavoriteListComponent {
    val favoriteListViewModel: FavoriteViewModel
}
