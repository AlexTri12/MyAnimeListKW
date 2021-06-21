package com.dicoding.android_expert.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListAnimeResponse(

    @field:SerializedName("request_hash")
    val requestHash: String,

    @field:SerializedName("request_cached")
    val requestCached: Boolean,

    @field:SerializedName("request_cached_expiry")
    val requestCacheExpiry: Int,

    @field:SerializedName("top")
    val topAnimes: List<AnimeResponse>
)