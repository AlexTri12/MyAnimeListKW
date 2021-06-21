package com.dicoding.android_expert.core.domain.model

data class ListAnime(
    var id: Int,
    var rank: Int,
    var title: String,
    var url: String,
    var imageUrl: String,
    var type: String,
    var episodes: Int,
    var startDate: String,
    var endDate: String?,
    var members: Int,
    var score: Double,
    var isFavorite: Boolean
)