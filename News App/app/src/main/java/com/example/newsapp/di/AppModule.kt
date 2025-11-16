package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.DataStoreManagerImpl
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.local.NewsDatabase
import com.example.newsapp.data.remote.BASE_URL
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.remote.repository.GetNewsRepositoryIml
import com.example.newsapp.domain.manager.DataStoreManager
import com.example.newsapp.domain.repository.GetNewsRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataStoreManager(
        application: Application
    ) : DataStoreManager = DataStoreManagerImpl(application)

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepoObj(
        newsApi: NewsApi
    ): GetNewsRepository = GetNewsRepositoryIml(newsApi)

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ) : NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = "news_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(
        newsDatabase: NewsDatabase
    ) : NewsDao = newsDatabase.dao()
}