package me.brisson.newsfeed.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val link: String? = null,
    val published: Long? = null,
    val created: Long? = null,
    val enclosures: List<Enclosure> = emptyList(),
    val media: Media? = null
): Parcelable


fun List<Item>.sortItemsByDate(): List<Item> {
    return this.sortedBy { it.created }.asReversed()
}