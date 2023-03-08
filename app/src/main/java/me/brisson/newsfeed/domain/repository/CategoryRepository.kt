package me.brisson.newsfeed.domain.repository

import me.brisson.newsfeed.domain.model.Result
import me.brisson.newsfeed.domain.model.Rss

interface CategoryRepository {

    suspend fun getAllCategories(): Result<List<String>>

    suspend fun getRssByCategory(category: String): Result<List<Rss>>
}