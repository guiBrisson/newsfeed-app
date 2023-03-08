@file:Suppress("DEPRECATION")

package me.brisson.newsfeed.util

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import me.brisson.newsfeed.domain.model.Item

class ItemNavType: NavType<Item>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Item? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, Item::class.java)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): Item {
        return Gson().fromJson(value, Item::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Item) {
        bundle.putParcelable(key, value)
    }
}