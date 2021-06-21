package com.dicoding.android_expert.myanimelistkw.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.android_expert.core.domain.usecase.MyAnimeListKWUseCase

class TVShowViewModel(useCase: MyAnimeListKWUseCase) : ViewModel() {

    val tvShowList = useCase.getAnimeTVShowList().asLiveData()

    val favoriteTVShowList = useCase.getFavoriteAnimeTVShowList().asLiveData()
}