package com.dicoding.android_expert.core.data.source.local.room

import androidx.room.*
import com.dicoding.android_expert.core.data.source.local.entity.DetailAnimeEntity
import com.dicoding.android_expert.core.data.source.local.entity.ListAnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MyAnimeListKWDao {

    @Query("SELECT * FROM list_anime WHERE type = :type")
    fun getTopAnime(type: String) : Flow<List<ListAnimeEntity>>

    @Query("SELECT * FROM list_anime WHERE type = :type AND is_favorite = 1")
    fun getFavoriteAnime(type: String) : Flow<List<ListAnimeEntity>>

    @Query("SELECT * FROM detail_anime WHERE id = :id")
    fun getDetailAnime(id: Int) : Flow<DetailAnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListAnime(topAnimes: List<ListAnimeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailAnime(detailAnime: DetailAnimeEntity)

    @Query("UPDATE list_anime SET is_favorite = :state WHERE id = :id")
    fun updateFavoriteListAnime(id: Int, state: Boolean)

    @Query("UPDATE detail_anime SET is_favorite = :state WHERE id = :id")
    fun updateFavoriteDetailAnime(id: Int, state: Boolean)
}