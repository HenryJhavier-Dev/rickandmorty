package com.henryjhavierdev.data.di

import com.henryjhavierdev.data.CharacterRepository
import com.henryjhavierdev.data.EpisodeRepository
import com.henryjhavierdev.data.datasource.CharacterRemoteDataSource
import com.henryjhavierdev.data.datasource.EpisodeRemoteDataSource
import com.henryjhavierdev.data.datasource.LocalCharacterDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun characterRepositoryProvider(
        remoteCharacterDataSource: CharacterRemoteDataSource,
        localCharacterDataSource: LocalCharacterDataSource
    ) = CharacterRepository(
        remoteCharacterDataSource,
        localCharacterDataSource
    )

    @Provides
    fun episodeRepositoryProvider(
        remoteEpisodeDataSource: EpisodeRemoteDataSource
    ) = EpisodeRepository(
        remoteEpisodeDataSource
    )
}
