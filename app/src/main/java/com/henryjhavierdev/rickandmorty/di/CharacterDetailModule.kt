package com.henryjhavierdev.rickandmorty.di

import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.rickandmorty.viewmodel.CharacterDetailDialogFragmentViewModel
import com.henryjhavierdev.usecases.GetEpisodeFromCharacterUseCase
import com.henryjhavierdev.usecases.GetFavoriteCharacterStatusUseCase
import com.henryjhavierdev.usecases.UpdateFavoriteCharacterStatusUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class CharacterDetailModule(
    private val character: Character?
) {

    @Provides
    fun characterDetailViewModelProvider(
        getEpisodeFromCharacterUseCase: GetEpisodeFromCharacterUseCase,
        getFavoriteCharacterStatusUseCase: GetFavoriteCharacterStatusUseCase,
        updateFavoriteCharacterStatusUseCase: UpdateFavoriteCharacterStatusUseCase
    ) = CharacterDetailDialogFragmentViewModel(
        character,
        getEpisodeFromCharacterUseCase,
        getFavoriteCharacterStatusUseCase,
        updateFavoriteCharacterStatusUseCase
    )
}

@Subcomponent(modules = [(CharacterDetailModule::class)])
interface CharacterDetailComponent {
    val characterDetailViewModel: CharacterDetailDialogFragmentViewModel
}
