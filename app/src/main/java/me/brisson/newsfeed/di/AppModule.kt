package me.brisson.newsfeed.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.brisson.newsfeed.data.NetworkConnectivityObserver
import me.brisson.newsfeed.data.data_source.remote.NewsFeedApi
import me.brisson.newsfeed.data.repository.CategoryRepositoryImpl
import me.brisson.newsfeed.data.repository.ChannelRepositoryImpl
import me.brisson.newsfeed.data.repository.RssRepositoryImpl
import me.brisson.newsfeed.domain.ConnectivityObserver
import me.brisson.newsfeed.domain.repository.CategoryRepository
import me.brisson.newsfeed.domain.repository.ChannelRepository
import me.brisson.newsfeed.domain.repository.RssRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(): NewsFeedApi {
        return Retrofit.Builder()
            .baseUrl("https://newsfeed-api-production.up.railway.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsFeedApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRssRepository(api: NewsFeedApi): RssRepository {
        return RssRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesChannelRepository(api: NewsFeedApi): ChannelRepository {
        return ChannelRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesCategoryRepository(api: NewsFeedApi): CategoryRepository {
        return CategoryRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesConnectivityObserver(@ApplicationContext appContext: Context): ConnectivityObserver {
        return NetworkConnectivityObserver(appContext)
    }

}
