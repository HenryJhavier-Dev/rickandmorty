package com.henryjhavierdev.rickandmorty.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.henryjhavierdev.rickandmorty.database.entity.EntityCharacter

@Dao
interface ICharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characters: List<EntityCharacter>)

    @Query("SELECT * FROM character_table")
    fun getAllCharacters(): LiveData<List<EntityCharacter>>

    @Query("SELECT * FROM character_table WHERE name LIKE :characterName LIMIT 1")
    suspend fun findByName(characterName: String): EntityCharacter

    @Query("SELECT * FROM character_table WHERE character_id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): LiveData<List<EntityCharacter>>

}
