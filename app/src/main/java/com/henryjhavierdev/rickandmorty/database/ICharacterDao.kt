package com.henryjhavierdev.rickandmorty.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.henryjhavierdev.rickandmorty.model.CharacterEntity
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface ICharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characters: List<CharacterEntity>)

    @Query("SELECT * FROM character_table")
    fun getAllCharacters(): LiveData<List<CharacterEntity>>

    @Query("SELECT * FROM character_table WHERE character_name LIKE :characterName LIMIT 1")
    suspend fun findByName(characterName: String): CharacterEntity

    @Query("SELECT * FROM character_table")
    fun getAllFavoriteCharacters(): Flowable<List<CharacterEntity>>

    @Query("SELECT * FROM character_table WHERE character_id = :id")
    fun getCharacterById(id: Int): Maybe<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(characterEntity: CharacterEntity)

    @Delete
    fun deleteCharacter(characterEntity: CharacterEntity)
}
