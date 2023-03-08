package me.brisson.newsfeed.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Media(
    val thumbnail: Enclosure? = null
): Parcelable
