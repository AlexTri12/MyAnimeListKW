package com.dicoding.android_expert.core.utils

import com.dicoding.android_expert.core.data.source.local.entity.DetailAnimeEntity
import com.dicoding.android_expert.core.data.source.local.entity.ListAnimeEntity
import com.dicoding.android_expert.core.data.source.remote.response.AnimeResponse
import com.dicoding.android_expert.core.data.source.remote.response.DetailAnimeResponse
import com.dicoding.android_expert.core.domain.model.DetailAnime
import com.dicoding.android_expert.core.domain.model.ListAnime

object DataMappers {

    fun mapResponseToEntity(input: List<AnimeResponse>) : List<ListAnimeEntity> {
        val animeList = ArrayList<ListAnimeEntity>()

        input.map {
            val anime = ListAnimeEntity(
                id = it.id,
                rank = it.rank,
                title = it.title,
                url = it.url,
                imageUrl = it.imageUrl,
                type = it.type,
                episodes = it.episodes,
                startDate = it.startDate,
                endDate = it.endDate,
                members = it.members,
                score = it.score,
                isFavorite = false
            )

            animeList.add(anime)
        }

        return animeList
    }

    fun mapResponseToEntity(input: DetailAnimeResponse) : DetailAnimeEntity {
        val otherTitle : String? = if (input.otherTitle.isNullOrEmpty()) null else input.otherTitle.joinToString(", ")

        var genres = ""
        for (genreResponse in input.genres) {
            genres += genreResponse.name + ", "
        }
        val concatedGenres = genres.substring(0, genres.length - 2)

        return DetailAnimeEntity(
            id = input.id,
            url = input.url,
            imageUrl = input.imageUrl,
            title = input.title,
            englishTitle = input.englishTitle,
            otherTitle = otherTitle,
            type = input.type,
            episodes = input.episodes,
            status = input.status,
            duration = input.duration,
            rating = input.rating,
            score = input.score,
            synopsis = input.synopsis,
            premiered = input.premiered,
            genres = concatedGenres,
            isFavorite = false
        )
    }

    fun mapEntityToDomain(input: List<ListAnimeEntity>) : List<ListAnime> {
        val animeList = ArrayList<ListAnime>()

        input.map {
            val anime = ListAnime(
                id = it.id,
                rank = it.rank,
                title = it.title,
                url = it.url,
                imageUrl = it.imageUrl,
                type = it.type,
                episodes = it.episodes,
                startDate = it.startDate,
                endDate = it.endDate,
                members = it.members,
                score = it.score,
                isFavorite = it.isFavorite
            )

            animeList.add(anime)
        }

        return animeList
    }

    fun mapEntityToDomain(input: DetailAnimeEntity?, animeId: Int) : DetailAnime {

        if (input == null || input.id != animeId) {
            return DetailAnime(
                id = 0,
                url = "",
                imageUrl = "",
                title = "",
                englishTitle = "",
                otherTitle = "",
                type = "",
                episodes = 0,
                status = "",
                duration = "",
                rating = "",
                score = 0.00,
                synopsis = "",
                premiered = "",
                genres = "",
                isFavorite = false
            )
        }

        return DetailAnime(
            id = input.id,
            url = input.url,
            imageUrl = input.imageUrl,
            title = input.title,
            englishTitle = input.englishTitle,
            otherTitle = input.otherTitle,
            type = input.type,
            episodes = input.episodes,
            status = input.status,
            duration = input.duration,
            rating = input.rating,
            score = input.score,
            synopsis = input.synopsis,
            premiered = input.premiered,
            genres = input.genres,
            isFavorite = input.isFavorite
        )
    }

    fun mapDomainToEntity(input: DetailAnime) : DetailAnimeEntity =
        DetailAnimeEntity(
            id = input.id,
            url = input.url,
            imageUrl = input.imageUrl,
            title = input.title,
            englishTitle = input.englishTitle,
            otherTitle = input.otherTitle,
            type = input.type,
            episodes = input.episodes,
            status = input.status,
            duration = input.duration,
            rating = input.rating,
            score = input.score,
            synopsis = input.synopsis,
            premiered = input.premiered,
            genres = input.genres,
            isFavorite = input.isFavorite
        )
}