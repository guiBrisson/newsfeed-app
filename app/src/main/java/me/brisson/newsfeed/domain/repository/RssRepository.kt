package me.brisson.newsfeed.domain.repository

import me.brisson.newsfeed.domain.model.Rss
import me.brisson.newsfeed.domain.model.Result

interface RssRepository {

    suspend fun getRssById(rssId: String) : Result<Rss>
}