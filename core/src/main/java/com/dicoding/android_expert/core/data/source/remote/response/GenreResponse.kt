package com.dicoding.android_expert.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenreResponse(

    @field:SerializedName("mal_id")
    val id: Int,

    @field:SerializedName("name")
    val name: String
)
