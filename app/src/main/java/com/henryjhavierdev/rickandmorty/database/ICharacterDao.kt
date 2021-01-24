package com.henryjhavierdev.rickandmorty.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ICharacterDao {

    @Query("DELETE FROM character_table ")
    suspend fun clear()

    @Query("SELECT * FROM character_table WHERE name LIKE :characterName LIMIT 1")
    suspend fun findByName(characterName: String): EntityCharacter

    @Query("SELECT * FROM character_table WHERE character_id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): LiveData<List<EntityCharacter>>

    @Insert
    suspend fun insertAll(character: EntityCharacter)

    @Delete
    suspend fun delete(character: EntityCharacter)

    @Update
    suspend fun update(character: EntityCharacter)

}
