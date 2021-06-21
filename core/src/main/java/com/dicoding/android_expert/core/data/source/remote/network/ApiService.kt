package com.dicoding.android_expert.core.data.source.remote.network

import com.dicoding.android_expert.core.data.source.remote.response.DetailAnimeResponse
import com.dicoding.android_expert.core.data.source.remote.response.ListAnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("top/anime/{page}/{type}")
    suspend fun getTopAnime(
        @Path("page") pageNumber: Int,
        @Path("type") type: String
    ) : ListAnimeResponse

    @GET("anime/{mal_id}")
    suspend fun getAnimeDetail(
        @Path("mal_id") id: Int
    ) : DetailAnimeResponse
}