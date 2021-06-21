package com.dicoding.android_expert.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AnimeResponse(

    @field:SerializedName("mal_id")
    val id: Int,

    @field:SerializedName("rank")
    val rank: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("episodes")
    val episodes: Int,

    @field:SerializedName("start_date")
    val startDate: String,

    @field:SerializedName("end_date")
    val endDate: String?,

    @field:SerializedName("members")
    val members: Int,

    @field:SerializedName("score")
    val score: Double
)