package com.dicoding.android_expert.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_anime")
data class ListAnimeEntity(

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "rank")
    var rank: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "image_url")
    var imageUrl: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "episodes")
    var episodes: Int,

    @ColumnInfo(name = "start_date")
    var startDate: String,

    @ColumnInfo(name = "end_date")
    var endDate: String?,

    @ColumnInfo(name = "members")
    var members: Int,

    @ColumnInfo(name = "score")
    var score: Double,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean
)