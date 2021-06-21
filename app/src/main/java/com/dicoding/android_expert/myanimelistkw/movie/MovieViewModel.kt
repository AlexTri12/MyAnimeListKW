package com.dicoding.android_expert.myanimelistkw.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.android_expert.core.domain.usecase.MyAnimeListKWUseCase

class MovieViewModel(useCase: MyAnimeListKWUseCase) : ViewModel() {

    val movieList = useCase.getAnimeMovieList().asLiveData()

    val favoriteMovieList = useCase.getFavoriteAnimeMovieList().asLiveData()
}