package com.dicoding.android_expert.core.di

import androidx.room.Room
import com.dicoding.android_expert.core.data.MyAnimeListKWRepository
import com.dicoding.android_expert.core.data.source.local.LocalDataSource
import com.dicoding.android_expert.core.data.source.local.room.MyAnimeListKWDatabase
import com.dicoding.android_expert.core.data.source.remote.RemoteDataSource
import com.dicoding.android_expert.core.data.source.remote.network.ApiService
import com.dicoding.android_expert.core.domain.repository.IMyAnimeListKWRepository
import com.dicoding.android_expert.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<MyAnimeListKWDatabase>().myAnimeListKWDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            MyAnimeListKWDatabase::class.java,
            "MyAnimeListKW.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single {
        LocalDataSource(get())
    }
    single {
        RemoteDataSource(get())
    }
    factory {
        AppExecutors()
    }
    single<IMyAnimeListKWRepository> {
        MyAnimeListKWRepository(get(), get(), get())
    }
}