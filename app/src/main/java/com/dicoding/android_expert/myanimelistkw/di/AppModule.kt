package com.dicoding.android_expert.myanimelistkw.di

import com.dicoding.android_expert.core.domain.usecase.MyAnimeListKWInteractor
import com.dicoding.android_expert.core.domain.usecase.MyAnimeListKWUseCase
import com.dicoding.android_expert.myanimelistkw.detail.DetailViewModel
import com.dicoding.android_expert.myanimelistkw.movie.MovieViewModel
import com.dicoding.android_expert.myanimelistkw.tvshow.TVShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MyAnimeListKWUseCase> {
        MyAnimeListKWInteractor(get())
    }
}

val viewModelModule = module {
    viewModel {
        MovieViewModel(get())
    }
    viewModel {
        TVShowViewModel(get())
    }
    viewModel {
        DetailViewModel(get())
    }
}