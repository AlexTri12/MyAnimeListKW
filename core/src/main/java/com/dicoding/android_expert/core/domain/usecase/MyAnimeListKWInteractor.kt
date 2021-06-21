package com.dicoding.android_expert.core.domain.usecase

import com.dicoding.android_expert.core.domain.model.DetailAnime
import com.dicoding.android_expert.core.domain.model.ListAnime
import com.dicoding.android_expert.core.domain.repository.IMyAnimeListKWRepository
import com.dicoding.android_expert.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class MyAnimeListKWInteractor(
    private val repository: IMyAnimeListKWRepository
) : MyAnimeListKWUseCase {

    override fun getAnimeTVShowList(): Flow<Resource<List<ListAnime>>> {
        return repository.getTopAnimeTVShowList()
    }

    override fun getAnimeMovieList(): Flow<Resource<List<ListAnime>>> {
        return repository.getTopAnimeMovieList()
    }

    override fun getFavoriteAnimeTVShowList(): Flow<List<ListAnime>> {
        return repository.getFavoriteAnimeTVShowList()
    }

    override fun getFavoriteAnimeMovieList(): Flow<List<ListAnime>> {
        return repository.getFavoriteAnimeMovieList()
    }

    override fun getDetailAnime(id: Int): Flow<Resource<DetailAnime>> {
        return repository.getDetailAnime(id)
    }

    override fun updateFavoriteAnime(anime: DetailAnime) {
        repository.setFavoriteAnime(anime)
    }
}