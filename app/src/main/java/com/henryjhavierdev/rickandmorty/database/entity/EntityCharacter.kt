package com.henryjhavierdev.rickandmorty.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class EntityCharacter (
    @PrimaryKey(autoGenerate = true)
    val character_id: Int? = 0 ,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "status")
    var status: String,

    @ColumnInfo(name = "species")
    var species: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "gender")
    var gender: String,

    @ColumnInfo(name = "origin")
    var origin: String,

    @ColumnInfo(name = "image")
    var image: String
)