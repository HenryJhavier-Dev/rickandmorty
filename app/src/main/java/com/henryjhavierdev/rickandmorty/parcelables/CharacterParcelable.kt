package com.henryjhavierdev.rickandmorty.parcelables

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class CharacterParcelable(
    var results: List<CharacterResultParcelable>
)

@Parcelize
data class CharacterResultParcelable(
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
    val origin: OriginParcelable?,
    @SerializedName("location")
    val location: LocationParcelable?,
    @SerializedName("episode")
    val episodeList: List<String>?
): Parcelable

@Parcelize
class OriginParcelable (
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("url")
    var url: String? = ""
): Parcelable

@Parcelize
class LocationParcelable (
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("url")
    var url: String? = ""
): Parcelable







