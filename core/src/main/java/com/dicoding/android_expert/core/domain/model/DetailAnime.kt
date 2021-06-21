package com.dicoding.android_expert.core.domain.model

data class DetailAnime(
    val id: Int,
    val url: String,
    val imageUrl: String,
    val title: String,
    val englishTitle: String?,
    val otherTitle: String?,
    val type: String,
    val episodes: Int,
    val status: String,
    val duration: String,
    val rating: String,
    val score: Double,
    val synopsis: String,
    val premiered: String?,
    val genres: String,
    val isFavorite: Boolean
)
