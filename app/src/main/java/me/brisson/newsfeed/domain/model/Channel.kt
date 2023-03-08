package me.brisson.newsfeed.domain.model

data class Channel(
    val id: String,
    val name: String,
    val rss: List<ChannelRss>
)
