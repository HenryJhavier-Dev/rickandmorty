package com.henryjhavierdev.rickandmorty.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Character(
    var info: Info? = null,
    var results: List<Result>
)

data class Result (
    var id: Int? = 0 ,
    @SerializedName("name_character")
    var name: String? = "",
    var status: String? = "",
    var species: String? = "",
    var type: String? = "",
    var gender: String? = "",
    var origin: Origin? = null,
    var location: Location? = null,
    var image: String? = "",
    var episode: List<String>? = null,
    var url: String? = "",
    var created: Date? = null
)

data class Info (
    var count:Int = 0,
    var pages:Int = 0,
    var next: String? = null,
    var prev: Any? = null,
)

class Origin (
    var name: String? = "",
    var url: String? = ""
)

class Location (
    var name: String? = "",
    var url: String? = ""
)



