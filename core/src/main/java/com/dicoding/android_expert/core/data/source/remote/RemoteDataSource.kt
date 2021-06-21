package com.dicoding.android_expert.core.data.source.remote

import android.util.Log
import com.dicoding.android_expert.core.data.source.remote.network.ApiResponse
import com.dicoding.android_expert.core.data.source.remote.network.ApiService
import com.dicoding.android_expert.core.data.source.remote.response.AnimeResponse
import com.dicoding.android_expert.core.data.source.remote.response.DetailAnimeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(
    private val apiService: ApiService
) {

    companion object {
        private const val MOVIE_TYPE = "movie"
        private const val TV_SHOW_TYPE = "tv"
        private const val PAGE_NUMBER = 1
    }

    suspend fun getTopListTVShowAnime() : Flow<ApiResponse<List<AnimeResponse>>> {
        return flow {
            try {
                val response = apiService.getTopAnime(PAGE_NUMBER, TV_SHOW_TYPE)
                val animes = response.topAnimes

                if (animes.isNotEmpty()) {
                    emit(ApiResponse.Success(response.topAnimes))
                    Log.e("RemoteDataSource", response.topAnimes.toString())
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTopListMovieAnime() : Flow<ApiResponse<List<AnimeResponse>>> {
        return flow {
            try {
                val response = apiService.getTopAnime(PAGE_NUMBER, MOVIE_TYPE)
                val animes = response.topAnimes

                if (animes.isNotEmpty()) {
                    emit(ApiResponse.Success(response.topAnimes))
                    Log.d("RemoteDataSource", response.topAnimes.toString())
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailAnime(id: Int) : Flow<ApiResponse<DetailAnimeResponse>> {
        return flow {
            try {
                Log.d("RemoteDataSource", "Anime with id $id")
                val response = apiService.getAnimeDetail(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}