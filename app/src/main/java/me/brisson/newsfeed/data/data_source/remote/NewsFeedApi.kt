package me.brisson.newsfeed.data.data_source.remote

import me.brisson.newsfeed.domain.model.Channel
import me.brisson.newsfeed.domain.model.Rss
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsFeedApi {

    @GET("/rss/{id}")
    suspend fun getRssById(@Path("id") rssId: String): Rss

    @GET("/channel/")
    suspend fun getAllChannel(): List<Channel>

    @GET("/channel/{id}")
    suspend fun getChannelById(@Path("id") channelId: String): Channel

    @GET("/category/")
    suspend fun getAllCategories(): List<String>

    @GET("/category/{category}")
    suspend fun getRssByCategory(@Path("category") category: String): List<Rss>
}