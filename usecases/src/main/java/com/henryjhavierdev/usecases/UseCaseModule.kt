package com.henryjhavierdev.usecases

import com.henryjhavierdev.data.CharacterRepository
import com.henryjhavierdev.data.EpisodeRepository
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun getAllCharacterUseCaseProvider(characterRepository: CharacterRepository) =
        GetAllCharactersUseCase(characterRepository)

    @Provides
    fun getAllFavoritesCharactersUserCaseProvider(characterRepository: CharacterRepository) =
        GetAllFavoriteCharactersUseCase(characterRepository)

    @Provides
    fun getFavoriteCharacterStatusUseCaseProvider(characterRepository: CharacterRepository) =
        GetFavoriteCharacterStatusUseCase(characterRepository)

    @Provides
    fun updateFavoriteCharacterStatusUseCaseProvider(characterRepository: CharacterRepository) =
        UpdateFavoriteCharacterStatusUseCase(characterRepository)

    @Provides
    fun getEpisodeFromCharacterUseCaseProvider(episodeRepository: EpisodeRepository) =
        GetEpisodeFromCharacterUseCase(episodeRepository)

}