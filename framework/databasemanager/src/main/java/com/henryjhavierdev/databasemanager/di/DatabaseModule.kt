package com.henryjhavierdev.databasemanager.di

import android.app.Application
import com.henryjhavierdev.data.datasource.LocalCharacterDataSource
import com.henryjhavierdev.databasemanager.CharacterDataBase
import com.henryjhavierdev.databasemanager.CharacterLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application): CharacterDataBase
        = CharacterDataBase.getInstanceDataBase(app)

    @Provides
    fun localCharacterDataSourceProvider(
        database: CharacterDataBase
    ): LocalCharacterDataSource = CharacterLocalDataSourceImpl(database)
}
