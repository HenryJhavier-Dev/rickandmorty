package com.henryjhavierdev.requestmanager.di

import com.henryjhavierdev.data.datasource.CharacterRemoteDataSource
import com.henryjhavierdev.data.datasource.EpisodeRemoteDataSource
import com.henryjhavierdev.requestmanager.CharacterRemoteDataSourceImpl
import com.henryjhavierdev.requestmanager.CharacterRequest
import com.henryjhavierdev.requestmanager.EpisodeRemoteDataSourceImpl
import com.henryjhavierdev.requestmanager.EpisodeRequest
import com.henryjhavierdev.requestmanager.utils.Constans.URL_BASE
import dagger.Module
import dagger.Provides
import retrofit2.http.Url
import javax.inject.Named
import javax.inject.Singleton

@Module
class ServiceModule {
    @Provides
    fun characterRequestProvider(
        @Named("baseUrl") baseUrl: String
    ) = CharacterRequest(baseUrl)

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrlProvider(): String = URL_BASE

    @Provides
    fun remoteCharacterDataSourceProvider(
        characterRequest: CharacterRequest
    ): CharacterRemoteDataSource = CharacterRemoteDataSourceImpl(characterRequest)

    @Provides
    fun episodeRequestProvider(
        @Named("baseUrl") baseUrl: String
    ) = EpisodeRequest(baseUrl)

    @Provides
    fun remoteEpisodeDataSourceProvider(
        episodeRequest: EpisodeRequest
    ): EpisodeRemoteDataSource = EpisodeRemoteDataSourceImpl(episodeRequest)
}