package com.dicoding.android_expert.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_anime")
data class DetailAnimeEntity(

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "image_url")
    var imageUrl: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "english_title")
    var englishTitle: String?,

    @ColumnInfo(name = "other_title")
    var otherTitle: String?,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "episodes")
    var episodes: Int,

    @ColumnInfo(name = "status")
    var status: String,

    @ColumnInfo(name = "duration")
    var duration: String,

    @ColumnInfo(name = "rating")
    var rating: String,

    @ColumnInfo(name = "score")
    var score: Double,

    @ColumnInfo(name = "synopsis")
    var synopsis: String,

    @ColumnInfo(name = "premiered")
    var premiered: String?,

    @ColumnInfo(name = "genres")
    var genres: String,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean
)
