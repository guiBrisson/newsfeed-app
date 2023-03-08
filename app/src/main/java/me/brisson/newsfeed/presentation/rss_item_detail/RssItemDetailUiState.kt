package me.brisson.newsfeed.presentation.rss_item_detail

import me.brisson.newsfeed.domain.model.Item

data class RssItemDetailUiState(
    val channelName: String = "",
    val item: Item? = null
)
