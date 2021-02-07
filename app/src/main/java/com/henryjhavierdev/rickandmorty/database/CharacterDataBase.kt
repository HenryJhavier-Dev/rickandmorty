package com.henryjhavierdev.rickandmorty.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.henryjhavierdev.rickandmorty.model.CharacterEntity
import com.henryjhavierdev.rickandmorty.utils.DATABASE_NAME

@Database(entities = [CharacterEntity::class], version = 2)
@TypeConverters(ListStringConverters::class)
abstract class CharacterDataBase : RoomDatabase() {

    abstract fun characterDao(): ICharacterDao

    companion object {

        @Volatile
        private var INSTANCE: CharacterDataBase? = null

        @Synchronized
        fun getInstanceDataBase(context: Context): CharacterDataBase {

            var instance = INSTANCE
            // If instance is `null` make a new database instance.
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDataBase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }

            return instance

        }
    }
}
