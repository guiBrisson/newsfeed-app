package me.brisson.newsfeed.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Enclosure(
    val type: String? = null,
    val url: String? = null
) : Parcelable
