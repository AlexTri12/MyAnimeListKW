package com.dicoding.android_expert.myanimelistkw.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.android_expert.core.domain.model.DetailAnime
import com.dicoding.android_expert.core.domain.usecase.MyAnimeListKWUseCase
import com.dicoding.android_expert.core.vo.Resource

class DetailViewModel(private val useCase: MyAnimeListKWUseCase) : ViewModel() {

    private var id = 0
    var detailAnime: LiveData<Resource<DetailAnime>>? = null

    fun setAnimeId(id: Int) {
        this.id = id
        detailAnime = useCase.getDetailAnime(id).asLiveData()
    }


    fun updateFavoriteAnime(anime: DetailAnime?) {
        if (anime != null) {
            useCase.updateFavoriteAnime(anime)
        }
    }
}