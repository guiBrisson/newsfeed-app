package me.brisson.newsfeed.data.repository

import me.brisson.newsfeed.data.data_source.remote.NewsFeedApi
import me.brisson.newsfeed.domain.model.Channel
import me.brisson.newsfeed.domain.model.Result
import me.brisson.newsfeed.domain.repository.ChannelRepository

class ChannelRepositoryImpl(private val api: NewsFeedApi) : ChannelRepository {
    override suspend fun getAllChannels(): Result<List<Channel>> {
        return try {
            Result.Success(api.getAllChannel())
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.Failure(ex)
        }
    }

    override suspend fun getChannelById(channelId: String): Result<Channel> {
        return try {
            Result.Success(api.getChannelById(channelId))
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.Failure(ex)
        }
    }
}