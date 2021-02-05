package com.henryjhavierdev.rickandmorty.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.henryjhavierdev.rickandmorty.database.entity.EntityCharacter
import com.henryjhavierdev.rickandmorty.utils.DATABASE_NAME

@Database(entities = [EntityCharacter::class], version = 1, exportSchema = false)
abstract class CharacterDataBase : RoomDatabase() {

    abstract val characterDao: ICharacterDao

    companion object {
        /**
         * INSTANCE will keep a reference to any database returned via getInstance.
         *
         * This will help us avoid repeatedly initializing the database, which is expensive.
         *
         *  The value of a volatile variable will never be cached, and all writes and
         *  reads will be done to and from the main memory. It means that changes made by one
         *  thread to shared data are visible to other threads.
         */
        @Volatile
        private var INSTANCE: CharacterDataBase? = null

        fun getInstanceDataBase(context: Context): CharacterDataBase {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this) {

                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CharacterDataBase::class.java,
                        DATABASE_NAME
                    )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // migration with Room in this blog post:
                        // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}
