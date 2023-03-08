package me.brisson.newsfeed.presentation.channel_home

import me.brisson.newsfeed.domain.model.Channel
import me.brisson.newsfeed.domain.model.ChannelRss
import me.brisson.newsfeed.domain.model.Result.Failure
import me.brisson.newsfeed.domain.model.Rss

data class ChannelHomeUiState(
    val loadingChannels: Boolean = false,
    val channels: List<Channel>? = null,
    val errorChannels: Failure? = null,

    val loadingChannel: Boolean = false,
    val channel: Channel? = null,
    val errorChannel: Failure? = null,

    val currentChannelRss: ChannelRss? = null,
    val currentCategory: String? = null,

    val loadingCategories: Boolean = false,
    val categories: List<String>? = null,
    val categoriesError: Failure? = null,

    val loadingRss: Boolean = false,
    val rss: List<Rss>? = null,
    val errorRss: Failure? = null,

    val refreshing: Boolean = false
)
