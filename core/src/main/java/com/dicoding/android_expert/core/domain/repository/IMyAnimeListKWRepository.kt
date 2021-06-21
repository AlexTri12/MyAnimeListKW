package com.dicoding.android_expert.core.domain.repository

import com.dicoding.android_expert.core.domain.model.DetailAnime
import com.dicoding.android_expert.core.domain.model.ListAnime
import com.dicoding.android_expert.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IMyAnimeListKWRepository {

    fun getTopAnimeTVShowList() : Flow<Resource<List<ListAnime>>>
    fun getTopAnimeMovieList() : Flow<Resource<List<ListAnime>>>
    fun getFavoriteAnimeTVShowList() : Flow<List<ListAnime>>
    fun getFavoriteAnimeMovieList() : Flow<List<ListAnime>>
    fun getDetailAnime(id: Int) : Flow<Resource<DetailAnime>>
    fun setFavoriteAnime(anime: DetailAnime)
}