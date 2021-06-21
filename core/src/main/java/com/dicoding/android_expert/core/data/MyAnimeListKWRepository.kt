package com.dicoding.android_expert.core.data

import com.dicoding.android_expert.core.data.source.local.LocalDataSource
import com.dicoding.android_expert.core.data.source.remote.RemoteDataSource
import com.dicoding.android_expert.core.data.source.remote.network.ApiResponse
import com.dicoding.android_expert.core.data.source.remote.response.AnimeResponse
import com.dicoding.android_expert.core.data.source.remote.response.DetailAnimeResponse
import com.dicoding.android_expert.core.domain.model.DetailAnime
import com.dicoding.android_expert.core.domain.model.ListAnime
import com.dicoding.android_expert.core.domain.repository.IMyAnimeListKWRepository
import com.dicoding.android_expert.core.utils.AppExecutors
import com.dicoding.android_expert.core.utils.DataMappers
import com.dicoding.android_expert.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MyAnimeListKWRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : IMyAnimeListKWRepository {

    override fun getTopAnimeTVShowList(): Flow<Resource<List<ListAnime>>> {
        return object : NetworkBoundResource<List<ListAnime>, List<AnimeResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<ListAnime>> {
                return localDataSource.getListTVShowAnime().map {
                    DataMappers.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<ListAnime>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<AnimeResponse>>> {
                return remoteDataSource.getTopListTVShowAnime()
            }

            override suspend fun saveCallResult(data: List<AnimeResponse>) {
                val animeList = DataMappers.mapResponseToEntity(data)
                localDataSource.insertTopListAnime(animeList)
            }
        }.asFlow()
    }

    override fun getTopAnimeMovieList(): Flow<Resource<List<ListAnime>>> {
        return object : NetworkBoundResource<List<ListAnime>, List<AnimeResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<ListAnime>> {
                return localDataSource.getListMovieAnime().map {
                    DataMappers.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<ListAnime>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<AnimeResponse>>> {
                return remoteDataSource.getTopListMovieAnime()
            }

            override suspend fun saveCallResult(data: List<AnimeResponse>) {
                val animeList = DataMappers.mapResponseToEntity(data)
                localDataSource.insertTopListAnime(animeList)
            }
        }.asFlow()
    }

    override fun getFavoriteAnimeTVShowList(): Flow<List<ListAnime>> {
        return localDataSource.getFavoriteAnimeTVShow().map {
            DataMappers.mapEntityToDomain(it)
        }
    }

    override fun getFavoriteAnimeMovieList(): Flow<List<ListAnime>> {
        return localDataSource.getFavoriteAnimeMovie().map {
            DataMappers.mapEntityToDomain(it)
        }
    }

    override fun getDetailAnime(animeId: Int): Flow<Resource<DetailAnime>> {
        return object : NetworkBoundResource<DetailAnime, DetailAnimeResponse>(appExecutors) {
            override fun loadFromDB(): Flow<DetailAnime> {
                return localDataSource.getDetailAnime(animeId).map {
                    DataMappers.mapEntityToDomain(it, animeId)
                }
            }

            override fun shouldFetch(data: DetailAnime?): Boolean {
                return data == null || data.id == 0
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailAnimeResponse>> {
                return remoteDataSource.getDetailAnime(animeId)
            }

            override suspend fun saveCallResult(data: DetailAnimeResponse) {
                val anime = DataMappers.mapResponseToEntity(data)
                localDataSource.insertNewDetailAnime(anime)
            }
        }.asFlow()
    }

    override fun setFavoriteAnime(anime: DetailAnime) {
        val animeEntity = DataMappers.mapDomainToEntity(anime)
        appExecutors.diskIO().execute {
            localDataSource.updateFavoriteAnime(animeEntity)
        }
    }
}