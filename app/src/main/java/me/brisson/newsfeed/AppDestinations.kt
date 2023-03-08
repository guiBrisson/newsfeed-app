package me.brisson.newsfeed

import android.net.Uri
import androidx.navigation.NavHostController
import com.google.gson.Gson
import me.brisson.newsfeed.AppDestinationArgs.CHANNEL_ID_ARG
import me.brisson.newsfeed.AppDestinationArgs.CHANNEL_NAME_ARG
import me.brisson.newsfeed.AppDestinationArgs.RSS_ITEM_ARGS
import me.brisson.newsfeed.AppScreens.CHANNEL_HOME_SCREEN
import me.brisson.newsfeed.AppScreens.RSS_ITEM_DETAIL_SCREEN
import me.brisson.newsfeed.domain.model.Item

private object AppScreens {
    const val CHANNEL_HOME_SCREEN = "channel_home"
    const val RSS_ITEM_DETAIL_SCREEN = "rss_item_detail"
}

object AppDestinationArgs {
    const val CHANNEL_ID_ARG = "channel_id"
    const val RSS_ITEM_ARGS = "rss_item"
    const val CHANNEL_NAME_ARG = "channel_name"
}

object AppDestinations {
    const val CHANNEL_HOME_ROUTE = "$CHANNEL_HOME_SCREEN/{$CHANNEL_ID_ARG}"
    const val RSS_ITEM_DETAIL_ROUTE = "$RSS_ITEM_DETAIL_SCREEN/{$RSS_ITEM_ARGS}/?{$CHANNEL_NAME_ARG}"
}

class AppNavigationActions(private val navController: NavHostController) {
    fun navigateToRssItemDetail(rss: Item, channelName: String) {
        val serializedItem = Uri.encode(Gson().toJson(rss))
        val route = "$RSS_ITEM_DETAIL_SCREEN/$serializedItem/?$channelName"
        navController.navigate(route){
            launchSingleTop = true
            restoreState = true
        }
    }
}