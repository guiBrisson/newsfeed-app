package me.brisson.newsfeed.data.repository

import me.brisson.newsfeed.data.data_source.remote.NewsFeedApi
import me.brisson.newsfeed.domain.model.Result
import me.brisson.newsfeed.domain.model.Rss
import me.brisson.newsfeed.domain.repository.RssRepository

class RssRepositoryImpl(
    private val api: NewsFeedApi
) : RssRepository {

    override suspend fun getRssById(rssId: String): Result<Rss> {
        return try {
            Result.Success(api.getRssById(rssId))
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.Failure(ex)
        }
    }
}