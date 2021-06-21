package com.dicoding.android_expert.core.data.source.local

import android.util.Log
import com.dicoding.android_expert.core.data.source.local.entity.DetailAnimeEntity
import com.dicoding.android_expert.core.data.source.local.entity.ListAnimeEntity
import com.dicoding.android_expert.core.data.source.local.room.MyAnimeListKWDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val databaseDao: MyAnimeListKWDao
) {

    companion object {
        private const val MOVIE_TYPE = "Movie"
        private const val TV_SHOW_TYPE = "TV"
    }

    fun getListTVShowAnime() : Flow<List<ListAnimeEntity>> =
        databaseDao.getTopAnime(TV_SHOW_TYPE)

    fun getListMovieAnime() : Flow<List<ListAnimeEntity>> =
        databaseDao.getTopAnime(MOVIE_TYPE)

    fun getFavoriteAnimeTVShow() : Flow<List<ListAnimeEntity>> =
        databaseDao.getFavoriteAnime(TV_SHOW_TYPE)

    fun getFavoriteAnimeMovie() : Flow<List<ListAnimeEntity>> =
        databaseDao.getFavoriteAnime(MOVIE_TYPE)

    fun getDetailAnime(id: Int) : Flow<DetailAnimeEntity> =
        databaseDao.getDetailAnime(id)

    suspend fun insertTopListAnime(animes: List<ListAnimeEntity>) =
        databaseDao.insertListAnime(animes)

    suspend fun insertNewDetailAnime(anime: DetailAnimeEntity) =
        databaseDao.insertDetailAnime(anime)

    fun updateFavoriteAnime(anime: DetailAnimeEntity) {
        val animeId = anime.id
        val newState = !anime.isFavorite

        Log.d("LocalDataSource", "Updating favorite anime $animeId and $newState")
        databaseDao.updateFavoriteListAnime(animeId, newState)
        databaseDao.updateFavoriteDetailAnime(animeId, newState)
    }
}