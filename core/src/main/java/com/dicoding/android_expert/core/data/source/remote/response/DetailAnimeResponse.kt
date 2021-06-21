package com.dicoding.android_expert.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailAnimeResponse(

    @field:SerializedName("mal_id")
    val id: Int,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("title_english")
    val englishTitle: String?,

    @field:SerializedName("title_synonyms")
    val otherTitle: List<String>,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("episodes")
    val episodes: Int,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("duration")
    val duration: String,

    @field:SerializedName("rating")
    val rating: String,

    @field:SerializedName("score")
    val score: Double,

    @field:SerializedName("synopsis")
    val synopsis: String,

    @field:SerializedName("premiered")
    val premiered: String?,

    @field:SerializedName("genres")
    val genres: List<GenreResponse>
)
