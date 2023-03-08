package me.brisson.newsfeed

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import me.brisson.newsfeed.presentation.channel_home.ChannelHomeScreen
import me.brisson.newsfeed.presentation.rss_item_detail.RssItemDetailScreen
import me.brisson.newsfeed.util.ItemNavType

@ExperimentalMaterialApi
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppDestinations.CHANNEL_HOME_ROUTE,
    navActions: AppNavigationActions = remember(navController) {
        AppNavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = AppDestinations.CHANNEL_HOME_ROUTE) {
            ChannelHomeScreen(
                onItem = { item, channelName ->
                    navActions.navigateToRssItemDetail(item, channelName)
                }
            )
        }

        composable(
            route = AppDestinations.RSS_ITEM_DETAIL_ROUTE,
            arguments = listOf(
                navArgument(name = AppDestinationArgs.RSS_ITEM_ARGS) {
                    type = ItemNavType()
                },
                navArgument(name = AppDestinationArgs.CHANNEL_NAME_ARG){
                    type = NavType.StringType
                }
            )
        ) {
            val uriHandler = LocalUriHandler.current
            RssItemDetailScreen(
                onBack = { navController.popBackStack() },
                onReadMore = { url -> uriHandler.openUri(url) }
            )
        }
    }

}