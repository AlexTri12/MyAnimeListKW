package com.dicoding.android_expert.core.domain.usecase

import com.dicoding.android_expert.core.domain.model.DetailAnime
import com.dicoding.android_expert.core.domain.model.ListAnime
import com.dicoding.android_expert.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface MyAnimeListKWUseCase {

    fun getAnimeTVShowList() : Flow<Resource<List<ListAnime>>>
    fun getAnimeMovieList() : Flow<Resource<List<ListAnime>>>
    fun getFavoriteAnimeTVShowList() : Flow<List<ListAnime>>
    fun getFavoriteAnimeMovieList() : Flow<List<ListAnime>>
    fun getDetailAnime(id: Int) : Flow<Resource<DetailAnime>>
    fun updateFavoriteAnime(anime: DetailAnime)
}