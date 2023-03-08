package me.brisson.newsfeed.domain.model

sealed class Result<out T> {
    data class Success<out R>(val value: R): Result<R>()
    data class Failure(
//        val message: String? = null,
        val exception: Exception? = null
    ): Result<Nothing>()
}
