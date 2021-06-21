package com.dicoding.android_expert.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.android_expert.core.data.source.local.entity.DetailAnimeEntity
import com.dicoding.android_expert.core.data.source.local.entity.ListAnimeEntity

@Database(
    entities = [ListAnimeEntity::class, DetailAnimeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MyAnimeListKWDatabase : RoomDatabase() {

    abstract fun myAnimeListKWDao() : MyAnimeListKWDao
}