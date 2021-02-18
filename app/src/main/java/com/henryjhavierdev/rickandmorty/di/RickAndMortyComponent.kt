package com.henryjhavierdev.rickandmorty.di

import android.app.Application
import com.henryjhavierdev.data.di.RepositoryModule
import com.henryjhavierdev.databasemanager.di.DatabaseModule
import com.henryjhavierdev.requestmanager.di.ServiceModule
import com.henryjhavierdev.usecases.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ServiceModule::class,
    DatabaseModule::class,
    RepositoryModule::class,
    UseCaseModule::class
])
interface RickAndMortyComponent {

    fun inject(module: CharacterListModule): CharacterListComponent
    fun inject(module: FavoriteListModule): FavoriteListComponent
    fun inject(module: CharacterDetailModule): CharacterDetailComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): RickAndMortyComponent
    }
}
