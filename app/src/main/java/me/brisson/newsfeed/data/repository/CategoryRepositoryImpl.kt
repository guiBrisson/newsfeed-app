package me.brisson.newsfeed.data.repository

import me.brisson.newsfeed.data.data_source.remote.NewsFeedApi
import me.brisson.newsfeed.domain.model.Result
import me.brisson.newsfeed.domain.model.Rss
import me.brisson.newsfeed.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val api: NewsFeedApi
): CategoryRepository {

    override suspend fun getAllCategories(): Result<List<String>> {
        return try {
            Result.Success(api.getAllCategories())
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.Failure(ex)
        }
    }

    override suspend fun getRssByCategory(category: String): Result<List<Rss>> {
        return try {
            Result.Success(api.getRssByCategory(category))
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.Failure(ex)
        }
    }
}