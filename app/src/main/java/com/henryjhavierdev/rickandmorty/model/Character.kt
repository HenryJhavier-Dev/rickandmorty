package com.henryjhavierdev.rickandmorty.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class Character(
    var results: List<CharacterResultRs>
)

@Parcelize
data class CharacterResultRs(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String?,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("origin")
    val origin: OriginRs,
    @SerializedName("location")
    val location: LocationRs,
    @SerializedName("episode")
    val episodeList: List<String>
): Parcelable

@Parcelize
class OriginRs (
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("url")
    var url: String? = ""
): Parcelable

@Parcelize
class LocationRs (
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("url")
    var url: String? = ""
): Parcelable






