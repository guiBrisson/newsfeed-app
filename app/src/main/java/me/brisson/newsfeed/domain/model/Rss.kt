package me.brisson.newsfeed.domain.model

data class Rss(
    val title: String? = null,
    val description: String? = null,
    val link: String? = null,
    val image: String? = null,
    val items: List<Item> = emptyList()
)

