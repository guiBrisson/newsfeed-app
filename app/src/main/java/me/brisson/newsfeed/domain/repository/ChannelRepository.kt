package me.brisson.newsfeed.domain.repository

import me.brisson.newsfeed.domain.model.Channel
import me.brisson.newsfeed.domain.model.Result

interface ChannelRepository {

    suspend fun getAllChannels() : Result<List<Channel>>

    suspend fun getChannelById(channelId: String): Result<Channel>
}